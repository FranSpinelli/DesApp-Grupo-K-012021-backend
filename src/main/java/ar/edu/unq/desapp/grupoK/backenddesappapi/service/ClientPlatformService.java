package ar.edu.unq.desapp.grupoK.backenddesappapi.service;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.ClientPlatform;
import ar.edu.unq.desapp.grupoK.backenddesappapi.persistence.ClientPlatformRepository;
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.serviceLevelExceptions.ClientAccessException;
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.serviceLevelExceptions.InexistentElementException;
import ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto.EmptyDTOException;
import ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto.LoginDTO;
import ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto.RegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClientPlatformService {

    @Autowired
    private ClientPlatformRepository clientPlatformRepository;

    public ResponseEntity registerNewClientPlatform(RegisterDTO registerDTO) throws EmptyDTOException, ClientAccessException {
        registerDTO.assertEmpty();

        isThereAPlatformWithSameName(registerDTO.getClientPlatformName());

        String generatedApiKey = generateApiKey();

        ClientPlatform newClientPlatform = new ClientPlatform(registerDTO.getClientPlatformName(), registerDTO.getPassword(), registerDTO.getContactMail(),
                    generatedApiKey);
        clientPlatformRepository.save(newClientPlatform);

        return ResponseEntity.ok().body(newClientPlatform);
    }

    public ResponseEntity loginClientPlatform(LoginDTO loginDTO) throws EmptyDTOException, ClientAccessException {

        loginDTO.assertEmpty();

        ClientPlatform clientPlatformWithName = clientPlatformRepository.findByName(loginDTO.getClientPlatformName());

        if(clientPlatformWithName == null || ! clientPlatformWithName.canLogInWithGivenPass(loginDTO.getPassword())){
            throw new ClientAccessException("Incorrect name or password");
        }

        return ResponseEntity.ok().body(clientPlatformWithName);
    }

    public ResponseEntity getApiKeyForClientPlatformWithName(String name) throws InexistentElementException {

        ClientPlatform clientPlatformWithName = clientPlatformRepository.findByName(name);

        if (clientPlatformWithName == null) {
            throw new InexistentElementException("There is no client platform with the given name");
        }

        return ResponseEntity.ok().body(clientPlatformWithName.getApiKey());
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
