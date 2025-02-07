package com.trianasalesianos.dam.ejemplosecurity.repository;

import com.trianasalesianos.dam.ejemplosecurity.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
