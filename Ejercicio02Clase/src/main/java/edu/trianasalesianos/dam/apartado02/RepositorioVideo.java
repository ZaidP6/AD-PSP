package edu.trianasalesianos.dam.apartado02;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioVideo extends JpaRepository<Video,Long> {
}
