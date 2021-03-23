package com.example.ensolvers.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.ensolvers.model.Task;
import com.example.ensolvers.repository.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService{
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    @Override
    public void saveTask(Task task) {
        this.taskRepository.save(task);
        
    }

    @Override
    public Task getTaskById(long id) {
        Optional <Task> optional = taskRepository.findById(id);
        Task task = null;
       
        if(optional.isPresent()){
            task = optional.get();            
        }else{
            throw new RuntimeException("Task not found");
        }
     
    
        return task;
    }

    @Override
    public void deleteTask(long id) {
        this.taskRepository.deleteById(id);        
    }

    @Override
    public List<Task> getAllTaskByFolder(long id) {
 
        List<Task> tasks =taskRepository.findAll();
        List<Task> task_filter = new ArrayList<>();
        System.out.println(tasks);
        System.out.println("aca");
        for (Task task : tasks) {
            if(task.getId_folder() == id){
                task_filter.add(task);
            }
        }
        return task_filter;
    }
}
