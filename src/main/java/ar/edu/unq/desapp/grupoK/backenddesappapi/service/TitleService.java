package ar.edu.unq.desapp.grupoK.backenddesappapi.service;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Title;
import ar.edu.unq.desapp.grupoK.backenddesappapi.persistence.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TitleService {

    @Autowired
    private TitleRepository repository;

    @Transactional
    public Title save(Title model) {
        return this.repository.save(model);
    }

    public Title findByID(Integer id) throws InexistentFilmWithIDError {
        try{
            return this.repository.findById(id).get();
        }catch(NoSuchElementException e){
            throw new InexistentFilmWithIDError("There is no Film with id: " + id);
        }
    }

    public List<Title> findAll() {
        return this.repository.findAll();
    }
}

