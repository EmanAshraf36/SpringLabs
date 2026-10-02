package com.nti.tasks.service;

import com.nti.tasks.exception.TaskNotFoundException;
import com.nti.tasks.model.Priority;
import com.nti.tasks.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaskService {

    private final Map<Long, Task> tasks = new LinkedHashMap<>();
    private long nextId = 1;

    public TaskService() {
        save(new Task(null, "AOP", false, Priority.HIGH));
        save(new Task(null, "MVC", false, Priority.MEDIUM));
        save(new Task(null, "SpringBOOT", false, Priority.LOW));

    }

    public synchronized Task save(Task task) {
        task.setId(nextId);
        nextId++;
        tasks.put(task.getId(), task);
        return task;

    }

    public synchronized List<Task> findAll() {
        return new ArrayList<>(tasks.values());
    }

    public synchronized Task findById(Long id) {
        Task task = tasks.get(id);
        if (task == null) {
            throw new TaskNotFoundException(id);
        }
        return task;
    }

    public synchronized List<Task> findByPriority(Priority priority) {
        List<Task> result = new ArrayList<>();
        for (Task task : tasks.values()) {
            if (task.getPriority() == priority) {
                result.add(task);
            }
        }
        return result;
    }

}
