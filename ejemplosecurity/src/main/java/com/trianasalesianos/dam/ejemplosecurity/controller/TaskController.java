package com.trianasalesianos.dam.ejemplosecurity.controller;

import com.trianasalesianos.dam.ejemplosecurity.model.Task;
import com.trianasalesianos.dam.ejemplosecurity.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/")
    public Task addNew(Task task){
        return taskService.addTask(task);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(Long id){
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/{id}")
    public Task findById(Long id){
        return taskService.findById(id);
    }
}
