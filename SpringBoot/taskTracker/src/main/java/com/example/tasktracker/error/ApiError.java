package com.example.tasktracker.error;

import java.time.Instant;

public record ApiError(

    int status,
    String error,
    String message,
    String path,
    Instant Timestamp) {}
