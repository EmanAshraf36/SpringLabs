package com.example.tasktracker.task;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class TaskStore { //singleton

    private final Map<Long, Task> tasks = new ConcurrentHashMap<>();
    private final AtomicLong nextId = new AtomicLong(1);

    public Task save(Task task) {
        if(task.getId() == null){
            task.setId(nextId.getAndIncrement());
        }
        tasks.put(task.getId(), task);
        return task;
    }

    public Optional<Task> findById(Long id) {
        return Optional.ofNullable(tasks.get(id));
    }

    public List<Task> findAll() {
        List<Task> all = new ArrayList<>(tasks.values());
        all.sort(Comparator.comparing(Task::getId));
        return all;
    }

    public boolean existsById(Long id) {
        return tasks.containsKey(id);
    }

    public boolean deleteById(Long id) {
        return tasks.remove(id) != null;
    }

    public int  count() {
        return tasks.size();
    }
}
