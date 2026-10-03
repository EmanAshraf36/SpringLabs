package com.example.tasktracker.task;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskStore store;
    private final int maxTasks;
    private final int defaultPageSize;

    public TaskController(TaskStore store,
                          @Value("${tasktracker.maxtasks}") int maxTasks,
                          @Value("${tasktracker.default-page-size}") int defaultPageSize){
        this.store = store;
        this.maxTasks = maxTasks;
        this.defaultPageSize = defaultPageSize;

    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Task task){
        requireTitle(task);
        if(store.count() >= maxTasks){
            throw new TaskLimitExceededException(maxTasks);
        }
        task.setId(null);
        Task saved = store.save(task);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(location).body(saved);
    }

    @GetMapping
    public ResponseEntity<?> list(@RequestParam(required = false) Boolean completed,
                                  @RequestParam(required = false) Integer limit){

        int size = (limit != null) ? limit : defaultPageSize;
        if(size < 1){
            throw new InvalidTaskException("Invalid limit specified");
        }

        List<Task> result = new ArrayList<>();
        for(Task t : store.findAll()){
            if (completed != null && t.isCompleted() != completed) {
                continue;
            }
            result.add(t);
            if (result.size() == size) {
                break;
            }
        }
        return ResponseEntity.ok(result);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<?> getById(@PathVariable Long id){
//        Optional<Task> task = store.findById(id);
//        if(task.isEmpty()){
//            return error(HttpStatus.NOT_FOUND, "Task not found");
//        }
//        return ResponseEntity.ok(task.get());
//    }

    @GetMapping("/{id}")
    public Task getById(@PathVariable Long id){
        return findOrThrow(id);
    }

//    @PutMapping
//    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody Task task){
//        if(!store.existsById(id)){
//            return error(HttpStatus.NOT_FOUND, "Task not found");
//        }
//        if(isBlank(task.getTitle())){
//            return error(HttpStatus.BAD_REQUEST, "title is required");
//        }
//        task.setId(id);
//        return ResponseEntity.ok(store.save(task));
//    }

    @PutMapping
    public Task update(@PathVariable Long id,@RequestBody Task task){
        findOrThrow(id);
        requireTitle(task);
        task.setId(id);
        return store.save(task);
    }

//    @PatchMapping("/{id}/complete")
//    public ResponseEntity<?> complete(@PathVariable Long id){
//        Optional<Task> found = store.findById(id);
//        if(found.isEmpty()){
//            return error(HttpStatus.NOT_FOUND, "Task not found");
//        }
//        Task task = found.get();
//        task.setCompleted(true);
//        return ResponseEntity.ok(task);
//    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<?> complete(@PathVariable Long id){
        Optional<Task> found = store.findById(id);
        if(found.isEmpty()){
            return error(HttpStatus.NOT_FOUND, "Task not found");
        }
        Task task = found.get();
        task.setCompleted(true);
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        if(!store.deleteById(id)){
            return error(HttpStatus.NOT_FOUND, "Task not found");
        }
        return ResponseEntity.noContent().build();
    }


    private boolean isBlank(String s) {
        return s == null || s.isBlank();
    }

    private ResponseEntity<Map<String, String>> error(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(Map.of("error", message));
    }


    private Task findOrThrow(Long id) {
        Optional<Task> found = store.findById(id);
        if (found.isEmpty()) {
            throw new TaskNotFoundException(id);
        }
        return found.get();
    }

    private void requireTitle(Task task) {
        if (task.getTitle() == null || task.getTitle().isBlank()) {
            throw new InvalidTaskException("title is required");   // -> 400
        }
    }
}
