package edu.trianasalesianos.dam.vizitable.user.repository;

import edu.trianasalesianos.dam.vizitable.user.model.ReviewRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReviewRateRepository extends JpaRepository<ReviewRate, UUID> {
}
