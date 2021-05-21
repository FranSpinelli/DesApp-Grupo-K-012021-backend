package ar.edu.unq.desapp.grupoK.backenddesappapi.service.serviceLevelExceptions;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Review;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Title;
import ar.edu.unq.desapp.grupoK.backenddesappapi.persistence.ReviewRepository;
import ar.edu.unq.desapp.grupoK.backenddesappapi.persistence.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.NoSuchElementException;

public abstract class AbstractService {

    @Autowired
    protected ReviewRepository reviewRepository;
    @Autowired
    protected TitleRepository titleRepository;

    protected Review findReviewByID(Integer id) throws InexistentReviewWithIDError {
        try{
            return this.reviewRepository.findById(id).get();
        }catch(NoSuchElementException e){
            throw new InexistentReviewWithIDError("There is no Review with id: " + id);
        }
    }

    protected Title findTitleByID(Integer id) throws InexistentTitleWithIDError {

        try{
            return titleRepository.findById(id).get();
        }catch(NoSuchElementException e){
            throw new InexistentTitleWithIDError("There is no Title with id: " + id);
        }
    }
}
