package ar.edu.unq.desapp.grupoK.backenddesappapi.service;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.ClientPlatform;
import ar.edu.unq.desapp.grupoK.backenddesappapi.persistence.ClientPlatformRepository;
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.serviceLevelExceptions.ClientAccessException;
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.serviceLevelExceptions.InexistentElementException;
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.serviceLevelExceptions.TokenValidationException;
import ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto.*;
import ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto.ResponseDTO.ApiKeyResponseDTO;
import ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto.ResponseDTO.TokenResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.UUID;

@Service
public class ClientPlatformService {

    @Autowired
    private ClientPlatformRepository clientPlatformRepository;

    public ResponseEntity registerNewClientPlatform(RegisterDTO registerDTO) throws ClientAccessException {

        isThereAPlatformWithSameName(registerDTO.getClientPlatformName());

        String generatedApiKey = generateApiKey();

        String encodedPassword = Base64.getEncoder().encodeToString(registerDTO.getPassword().getBytes());

        ClientPlatform newClientPlatform = new ClientPlatform(registerDTO.getClientPlatformName(), encodedPassword, registerDTO.getContactMail(),
                    generatedApiKey);
        clientPlatformRepository.save(newClientPlatform);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity loginClientPlatform(LoginDTO loginDTO) throws ClientAccessException {

        ClientPlatform clientPlatformWithName = clientPlatformRepository.findByName(loginDTO.getClientPlatformName());

        String encodedPassword = Base64.getEncoder().encodeToString(loginDTO.getPassword().getBytes());

        if(clientPlatformWithName == null || ! clientPlatformWithName.canLogInWithGivenPass(encodedPassword)){
            throw new ClientAccessException("Incorrect name or password");
        }


        String token = JwtToken.getTokenFor(clientPlatformWithName.getName());
        return ResponseEntity.ok().body(new TokenResponseDTO(token));
    }

    public ResponseEntity getApiKeyForClientPlatformWithName(String name, String token) throws InexistentElementException, TokenValidationException {

        ClientPlatform clientPlatformWithName = clientPlatformRepository.findByName(name);

        if (clientPlatformWithName == null) {
            throw new InexistentElementException("There is no client platform with the given name");
        }

        JwtToken.isValidToken(token.split(" ")[1],name);

        return ResponseEntity.ok().body(new ApiKeyResponseDTO(clientPlatformWithName.getApiKey()));
    }

    private String generateApiKey() {
        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();

        return uuidAsString;
    }

    private void isThereAPlatformWithSameName(String clientPlatformName) throws ClientAccessException {

        ClientPlatform clientWithName = clientPlatformRepository.findByName(clientPlatformName);
        if(clientWithName != null){throw new ClientAccessException("The provided name is already in use");}
    }
}
