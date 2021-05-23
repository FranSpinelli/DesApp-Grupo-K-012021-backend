package ar.edu.unq.desapp.grupoK.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupoK.backenddesappapi.service.ClientPlatformService;
import ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto.LoginDTO;
import ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto.RegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping("/clientPlatform")
public class ClientPlatformController {

    @Autowired
    private ClientPlatformService clientPlatformService;

    @PostMapping("/register")
    public ResponseEntity registerWith(@RequestBody RegisterDTO registerDTO) {
        return clientPlatformService.registerNewClientPlatform(registerDTO);
    }

    @PostMapping("/login")
    public ResponseEntity loginWith(@RequestBody LoginDTO loginDTO){
        return clientPlatformService.loginClientPlatform(loginDTO);
    }
}
