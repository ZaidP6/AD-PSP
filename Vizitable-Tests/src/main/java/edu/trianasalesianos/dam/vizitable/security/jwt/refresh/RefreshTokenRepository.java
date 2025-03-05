package edu.trianasalesianos.dam.vizitable.security.jwt.refresh;

import edu.trianasalesianos.dam.vizitable.user.model.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.UUID;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {

    @Modifying
    @Transactional
    void deleteByUser(Usuario user);
}
