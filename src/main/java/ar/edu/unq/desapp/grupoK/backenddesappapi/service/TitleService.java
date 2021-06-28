package ar.edu.unq.desapp.grupoK.backenddesappapi.service;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.ClientPlatform;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Title;
import ar.edu.unq.desapp.grupoK.backenddesappapi.persistence.ClientPlatformRepository;
import ar.edu.unq.desapp.grupoK.backenddesappapi.persistence.TitleRepository;
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.serviceLevelExceptions.InexistentElementException;
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.serviceLevelExceptions.TokenValidationException;
import ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto.TitleSubscriptionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TitleService extends AbstractService{

    @Autowired
    private ClientPlatformRepository clientPlatformRepository;

    @Autowired
    private TitleRepository titleRepository;

    @Transactional
    public List<Title> findAll() {
        return this.titleRepository.findAll();
    }

    public ResponseEntity addSubscriberToClient(TitleSubscriptionDTO titleSubscriptionDTO, String token) throws InexistentElementException, TokenValidationException {

        String name = findClientById(titleSubscriptionDTO.getSubscriberClientID()).getName();
        JwtToken.isValidToken(token.split(" ")[1],name);

        Title titlewithID = findTitleByID(titleSubscriptionDTO.getTitleID());

        titlewithID.addSubscriber(titleSubscriptionDTO.getEndpointToReach());
        titleRepository.save(titlewithID);

        return ResponseEntity.ok().build();
    }

    private ClientPlatform findClientById(Integer id) throws InexistentElementException {

        try{
            return clientPlatformRepository.findById(id).get();
        }catch(NoSuchElementException e){
            throw new InexistentElementException("There is no Client with id: " + id);
        }
    }
}

