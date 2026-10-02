package com.nti.tasks.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Task {

    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    private boolean completed;

    @NotNull(message = "Priority is required")
    private Priority priority;

    public Task() {}

    public Task(Long id, String title, boolean completed, Priority priority) {
        this.id = id;
        this.title = title;
        this.completed = completed;
        this.priority = priority;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}
