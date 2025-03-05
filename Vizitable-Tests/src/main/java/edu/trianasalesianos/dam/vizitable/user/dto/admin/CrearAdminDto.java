package edu.trianasalesianos.dam.vizitable.user.dto.admin;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CrearAdminDto(
        @NotBlank(message = "El username no puede estar vacío")
        @Size(min=3, max=20, message = "El username debe tener entre 3 y 20 caracteres")
        String username,

        @NotBlank(message = "El email no puede estar vacío")
        @Email(message = "El email debe tener un formato válido")
        String email,

        @NotBlank(message = "La contraseña no puede estar vacía")
        @Size(min=8, message = "La contraseña debe tener al menos 8 caracteres")
        @Pattern(
                regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).*$",
                message = "La contraseña debe contener al menos una mayúscula, " +
                        "na minúscula, un número y un carácter especial (@#$%^&+=!)"
        )
        String password,

        @NotBlank(message = "Debe repetir la contraseña")
        String verifyPassword,

        @NotBlank
        String inviteCode
) {
}
