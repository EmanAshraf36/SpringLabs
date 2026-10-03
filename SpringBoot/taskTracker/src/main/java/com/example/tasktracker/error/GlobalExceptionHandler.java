package com.example.tasktracker.error;

import com.example.tasktracker.task.InvalidTaskException;
import com.example.tasktracker.task.TaskLimitExceededException;
import com.example.tasktracker.task.TaskNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(TaskNotFoundException.class) //404
    public ResponseEntity<ApiError> handleNotFound(TaskNotFoundException ex, HttpServletRequest request) {
        return build(HttpStatus.NOT_FOUND, ex.getMessage(), request);
    }

    @ExceptionHandler(TaskLimitExceededException.class) //409
    public ResponseEntity<ApiError> handleLimit(TaskLimitExceededException ex, HttpServletRequest request) {
        return build(HttpStatus.CONFLICT, ex.getMessage(), request);
    }

    @ExceptionHandler(InvalidTaskException.class)
    public ResponseEntity<ApiError> handleInvalid(InvalidTaskException ex, HttpServletRequest request) {
        return build(HttpStatus.BAD_REQUEST, ex.getMessage(), request);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiError> handleTypeMismatch(MethodArgumentTypeMismatchException ex,
                                                       HttpServletRequest request) {
        String message = "Invalid value '" + ex.getValue() + "' for parameter '" + ex.getName() + "'";
        return build(HttpStatus.BAD_REQUEST, message, request);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleUnreadableBody(HttpMessageNotReadableException ex,
                                                         HttpServletRequest request) {
        return build(HttpStatus.BAD_REQUEST, "Request body is missing or is not valid JSON", request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleEverythingElse(Exception ex, HttpServletRequest request) {

        if (ex instanceof ErrorResponse springError) {
            int code = springError.getStatusCode().value();
            HttpStatus status = HttpStatus.resolve(code);
            if (status == null) {
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
            return build(status, ex.getMessage(), request);
        }
        log.error("Unexpected error on {} {}", request.getMethod(), request.getRequestURI(), ex);
        return build(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong. Please try again later.", request);
    }




    private ResponseEntity<ApiError> build(HttpStatus status, String message, HttpServletRequest request) {
        HttpStatus known = HttpStatus.resolve(status.value());
        String errorName = (known != null) ? known.getReasonPhrase() : "Error";

        ApiError body = new ApiError(
                status.value(),
                errorName,
                message,
                request.getRequestURI(),
                Instant.now());

        return ResponseEntity.status(status).body(body);
    }
}
