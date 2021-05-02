package ar.edu.unq.desapp.grupoK.backenddesappapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
@RestController
public class BackendDesappApiApplication {

    public static void main(String[] args) {
        System.setProperty("server.servlet.context-path", "/re-seña");
        SpringApplication.run(BackendDesappApiApplication.class, args);
    }
}
