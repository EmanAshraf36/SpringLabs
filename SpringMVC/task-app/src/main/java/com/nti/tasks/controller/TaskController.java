package com.nti.tasks.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.nti.tasks.model.Priority;
import com.nti.tasks.model.Task;
import com.nti.tasks.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
public class TaskController{

    private final TaskService taskService;

    public TaskController(TaskService taskService) {

        this.taskService = taskService;
    }

    @GetMapping
    public String listTasks(Model model){
        model.addAttribute("tasks",taskService.findAll());
        return "tasks/list";
    }

    @GetMapping("/search")
    public String searchByPriority( @RequestParam(name ="priority", defaultValue = "HIGH") Priority priority, Model model){

        model.addAttribute("tasks",taskService.findByPriority(priority));
        return "tasks/list";

    }

    @GetMapping("/{id}")
    public String showTask(@PathVariable Long id, Model model){
        model.addAttribute("task",taskService.findById(id));
        return "tasks/detail";
    }

    // ====================== form =================

    @ModelAttribute("priorities")
    public Priority[] priorities(){
        return Priority.values();
    }

    @GetMapping("/new")
    public String showCreateForm(Model model){
        model.addAttribute("task",new Task());
        return "tasks/form";
    }

    @PostMapping
    public String createTask(@Valid @ModelAttribute("task") Task task,
                             BindingResult result) {

        if (result.hasErrors()) {
            return "tasks/form";      // redisplay the form, with the errors
        }

        taskService.save(task);
        return "redirect:/tasks";
    }

}

