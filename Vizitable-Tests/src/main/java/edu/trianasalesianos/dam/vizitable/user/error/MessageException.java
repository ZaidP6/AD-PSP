package edu.trianasalesianos.dam.vizitable.user.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MessageException extends RuntimeException {

    private final HttpStatus status;
    private final ErrorType errorType;

    public MessageException(ErrorType errorType, HttpStatus status) {

        super(errorType.getMessage());
        this.status = errorType.getStatus();
        this.errorType = errorType;
    }


    public enum ErrorType {
        USERNAME_EXISTS("El nombre de usuario ya existe", HttpStatus.BAD_REQUEST),
        EMAIL_EXISTS("El email ya existe", HttpStatus.BAD_REQUEST),
        PASSWORD_MISMATCH("Las contraseñas no coinciden", HttpStatus.BAD_REQUEST),
        USER_NOT_FOUND("Usuario no encontrado", HttpStatus.NOT_FOUND),
        LUGAR_NOT_FOUND("Lugar no encontrado", HttpStatus.NOT_FOUND),
        SUGERENCIA_NOT_FOUND("Sugerencia de estado no encontrada", HttpStatus.NOT_FOUND),
        SUGERENCIA_REJECTED("Esta sugerencia ya ha sido rechazada antes", HttpStatus.BAD_REQUEST),
        SUGERENCIA_ALREADY_APPROVED("Esta sugerencia ya ha sido aprobada", HttpStatus.BAD_REQUEST),
        SUGERENCIA_DUPLICATE("El usuario ya tiene una sugerencia en curso de este lugar", HttpStatus.CONFLICT),
        FAVORITOS_EMPTY("La lista de favoritos está vacía", HttpStatus.NO_CONTENT),
        NO_USERS_FOUND("No hay usuarios registrados", HttpStatus.NO_CONTENT),
        OTP_CODE_INVALID("Código 2FA inválido", HttpStatus.UNAUTHORIZED),
        LUGAR_ALREADY_IN_FAVORITES("Este lugar ya está en favoritos", HttpStatus.CONFLICT),
        LUGAR_NOT_IN_FAVORITES("Este lugar no está en favoritos", HttpStatus.CONFLICT),
        LUGAR_ALREADY_EXISTS("Este lugar ya existe", HttpStatus.CONFLICT );



        @Getter
        private final String message;
        @Getter
        private final HttpStatus status;

        ErrorType(String message, HttpStatus status) {
            this.message = message;
            this.status = status;
        }
    }
}


