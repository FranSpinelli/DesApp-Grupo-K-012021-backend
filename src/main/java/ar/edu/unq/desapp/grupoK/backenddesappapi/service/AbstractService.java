package ar.edu.unq.desapp.grupoK.backenddesappapi.service;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Review;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Title;
import ar.edu.unq.desapp.grupoK.backenddesappapi.persistence.ReviewRepository;
import ar.edu.unq.desapp.grupoK.backenddesappapi.persistence.TitleRepository;
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.serviceLevelExceptions.InexistentElementException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.NoSuchElementException;

public abstract class AbstractService {

    @Autowired
    protected ReviewRepository reviewRepository;
    @Autowired
    protected TitleRepository titleRepository;

    protected Review findReviewByID(Integer id) throws InexistentElementException {
        try{
            return this.reviewRepository.findById(id).get();
        }catch(NoSuchElementException e){
            throw new InexistentElementException("There is no Review with id: " + id);
        }
    }

    protected Title findTitleByID(Integer id) throws InexistentElementException {

        try{
            return titleRepository.findById(id).get();
        }catch(NoSuchElementException e){
            throw new InexistentElementException("There is no Title with id: " + id);
        }
    }
}
