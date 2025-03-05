package edu.trianasalesianos.dam.vizitable;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(title = "API de Vizitable", version = "1.0", description = "Documentación de la API")
)
@SpringBootApplication
public class VizitableApplication {

    public static void main(String[] args) {
        SpringApplication.run(VizitableApplication.class, args);
    }

}
