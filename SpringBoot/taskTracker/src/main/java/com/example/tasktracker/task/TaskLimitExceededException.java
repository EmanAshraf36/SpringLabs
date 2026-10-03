package com.example.tasktracker.task;

public class TaskLimitExceededException extends RuntimeException{

    public TaskLimitExceededException(int maxTasks) {
        super("Task limit exceeded, task limit:"+ maxTasks);
    }
}
