package ar.edu.unq.desapp.grupoK.backenddesappapi.service;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.ClientPlatform;
import ar.edu.unq.desapp.grupoK.backenddesappapi.persistence.ClientPlatformRepository;
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.serviceLevelExceptions.ClientPlatformLoginException;
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.serviceLevelExceptions.NameAlreadyInUseException;
import ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto.EmptyDTOError;
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

    public ResponseEntity registerNewClientPlatform(RegisterDTO registerDTO) {

        try {
            registerDTO.assertEmpty();

            isThereAPlatformWithSameName(registerDTO.getClientPlatformName());

            String generatedApiKey = generateApiKey();

            ClientPlatform newClientPlatform = new ClientPlatform(registerDTO.getClientPlatformName(), registerDTO.getPassword(), registerDTO.getContactMail(),
                    generatedApiKey);
            clientPlatformRepository.save(newClientPlatform);

            return ResponseEntity.ok().body(newClientPlatform);

        } catch (NameAlreadyInUseException | EmptyDTOError e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    public ResponseEntity loginClientPlatform(LoginDTO loginDTO) {

        try{
            loginDTO.assertEmpty();

            ClientPlatform clientPlatformWithName = clientPlatformRepository.findByName(loginDTO.getClientPlatformName());

            if(clientPlatformWithName == null || ! clientPlatformWithName.canLogInWithGivenPass(loginDTO.getPassword())){
                throw new ClientPlatformLoginException();
            }

            return ResponseEntity.ok().body(clientPlatformWithName);
        }catch(ClientPlatformLoginException | EmptyDTOError e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    private String generateApiKey() {
        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();

        return uuidAsString;
    }

    private void isThereAPlatformWithSameName(String clientPlatformName) throws NameAlreadyInUseException {

        ClientPlatform clientWithName = clientPlatformRepository.findByName(clientPlatformName);
        if(clientWithName != null){throw new NameAlreadyInUseException();}
    }
}
