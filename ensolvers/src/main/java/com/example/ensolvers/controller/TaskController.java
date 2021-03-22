package com.example.ensolvers.controller;

import java.util.Scanner;

import com.example.ensolvers.model.Task;
import com.example.ensolvers.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;



    @GetMapping("/")
    public String viewHomePage(Model model){
        model.addAttribute("listTask",taskService.getAllTask());
        return "index";
    }

    @GetMapping("/showNewTaskForm")
    public String showNewTaskForm(Model model){
        System.out.println("hola");
        
        Task task = new Task();
        model.addAttribute("task", task);
        return "new_task";
        
    }

    @PostMapping("/saveTask")
    public String saveTask(@ModelAttribute("task") Task task){
        taskService.saveTask(task);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable (value = "id") long id, Model model){
        //get task from the list
        Task task = taskService.getTaskById(id);

        model.addAttribute("task", task);
     
        return "edit_task";

    }

    @GetMapping("/deleteTask/{id}")
	public String deleteTask(@PathVariable (value = "id") long id) {
		
		// call delete employee method 
		this.taskService.deleteTask(id);
		return "redirect:/";
	}

}
