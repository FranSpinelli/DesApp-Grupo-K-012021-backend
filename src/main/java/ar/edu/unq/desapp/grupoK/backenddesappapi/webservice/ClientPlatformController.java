package ar.edu.unq.desapp.grupoK.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupoK.backenddesappapi.service.ClientPlatformService;
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.serviceLevelExceptions.ClientAccessException;
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.serviceLevelExceptions.InexistentElementException;
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.serviceLevelExceptions.TokenValidationException;
import ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto.LoginDTO;
import ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto.RegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@EnableAutoConfiguration
@RequestMapping("/clientPlatform")
public class ClientPlatformController {

    @Autowired
    private ClientPlatformService clientPlatformService;

    @PostMapping("/register")
    public ResponseEntity registerWith(@RequestBody @Valid RegisterDTO registerDTO) throws ClientAccessException {
        return clientPlatformService.registerNewClientPlatform(registerDTO);
    }

    @PutMapping("/login")
    public ResponseEntity loginWith(@RequestBody @Valid LoginDTO loginDTO) throws ClientAccessException {
        return clientPlatformService.loginClientPlatform(loginDTO);
    }

    @GetMapping("/apiKey")
    public ResponseEntity getApiKey(@RequestParam String name, @RequestHeader String token) throws InexistentElementException, TokenValidationException {
        return clientPlatformService.getApiKeyForClientPlatformWithName(name, token);
    }
}
