package edu.trianasalesianos.dam.vizitable.user.repository;

import edu.trianasalesianos.dam.vizitable.user.dto.user.PerfilResponseDTO;
import edu.trianasalesianos.dam.vizitable.user.model.Lugar;
import edu.trianasalesianos.dam.vizitable.user.model.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    @EntityGraph(attributePaths = {"lugaresFavoritos"})
    Optional<Usuario> findFirstByUsername(String username);

    @Query("SELECT new edu.trianasalesianos.dam.vizitable.user.dto.user" +
            ".PerfilResponseDTO(u.uuid, u.email, u.username, u.fullname, u.fotoPerfil) " +
            "FROM Usuario u " +
            "WHERE u.username = :username")
    Optional<PerfilResponseDTO> findPerfilByUsername(@Param("username") String username);


    boolean existsByUsername(
            @NotBlank(message = "El username no puede estar vacío")
            @Size(min=3, max=20,message = "El username debe tener entre 3 y 20 caracteres") String username);


    boolean existsByEmail(
            @NotBlank(message = "El email no puede estar vacío")
            @Email(message = "El email debe tener un formato válido") String email);

    @Modifying
    @Query("UPDATE Usuario u SET u.lugaresFavoritos = (SELECT l FROM Lugar l WHERE l.id NOT IN :lugar) WHERE :lugar MEMBER OF u.lugaresFavoritos")
    void removeLugarFromAllUsuarios(
            @NotBlank Lugar lugar);
}
