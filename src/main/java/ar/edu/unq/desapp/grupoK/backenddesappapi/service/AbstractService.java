package ar.edu.unq.desapp.grupoK.backenddesappapi.service;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Review;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Title;
import ar.edu.unq.desapp.grupoK.backenddesappapi.persistence.ReviewRepository;
import ar.edu.unq.desapp.grupoK.backenddesappapi.persistence.TitleRepository;
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.serviceLevelExceptions.InexistentElementWithIDException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.NoSuchElementException;

public abstract class AbstractService {

    @Autowired
    protected ReviewRepository reviewRepository;
    @Autowired
    protected TitleRepository titleRepository;

    protected Review findReviewByID(Integer id) throws InexistentElementWithIDException {
        try{
            return this.reviewRepository.findById(id).get();
        }catch(NoSuchElementException e){
            throw new InexistentElementWithIDException("There is no Review with id: " + id);
        }
    }

    protected Title findTitleByID(Integer id) throws InexistentElementWithIDException {

        try{
            return titleRepository.findById(id).get();
        }catch(NoSuchElementException e){
            throw new InexistentElementWithIDException("There is no Title with id: " + id);
        }
    }
}
