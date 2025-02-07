package com.trianasalesianos.dam.ejemplosecurity.service;

import com.trianasalesianos.dam.ejemplosecurity.model.Task;
import com.trianasalesianos.dam.ejemplosecurity.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private TaskRepository taskRepository;

    public Task addTask(Task task){
        return taskRepository.save(task);
    }

    public Task findById(Long id){
        return taskRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }


    public List<Task> findAll(){
        return taskRepository.findAll();
    }
    public void delete(Long id){
        taskRepository.deleteById(id);
    }
}
