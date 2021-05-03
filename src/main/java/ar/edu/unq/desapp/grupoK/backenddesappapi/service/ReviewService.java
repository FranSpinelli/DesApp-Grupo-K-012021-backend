package ar.edu.unq.desapp.grupoK.backenddesappapi.service;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.PremiumReview;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.PublicReview;
import ar.edu.unq.desapp.grupoK.backenddesappapi.persistence.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repository;

    @Transactional
    public PremiumReview save(PremiumReview model) {
        return this.repository.save(model);
    }

    public PremiumReview findByID(Integer id) throws InexistentReviewWithIDError {
        try{
            return this.repository.findById(id).get();
        }catch(NoSuchElementException e){
            throw new InexistentReviewWithIDError("There is no Review with id: " + id);
        }
    }
    public List<PremiumReview> findAllPremiumReviews(){
        return this.repository.findAllPremiumreviews();
    }

    public List<PublicReview> findAllPublicReviews(){
        return this.repository.findAllPublicReviews();
    }

    public List<PremiumReview> findAll() {
        return this.repository.findAll();
    }
}

