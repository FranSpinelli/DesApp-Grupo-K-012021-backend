package ar.edu.unq.desapp.grupoK.backenddesappapi.service;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Title;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TitleService extends AbstractService{

    @Transactional
    public List<Title> findAll() {
        return this.titleRepository.findAll();
    }
}

