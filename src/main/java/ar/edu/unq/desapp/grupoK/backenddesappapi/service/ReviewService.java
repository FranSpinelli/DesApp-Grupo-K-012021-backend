package ar.edu.unq.desapp.grupoK.backenddesappapi.service;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.PremiumReview;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Review;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.PublicReview;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Title;
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.serviceLevelExceptions.InexistentReviewWithIDException;
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.serviceLevelExceptions.InexistentTitleWithIDException;
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.serviceLevelExceptions.RepeatedReviewException;
import ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto.EmptyDTOError;
import ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto.PremiumReviewDTO;
import ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto.PublicReviewDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;

@Service
public class ReviewService extends AbstractService {

    @Transactional
    public ResponseEntity addNewPremiumReview(PremiumReviewDTO premiumReviewDTO) {

        try {
            premiumReviewDTO.assertEmpty();

            Title titleWithID = findTitleByID(premiumReviewDTO.getTitleID());

            checkForRepeatedPremiumReviewInTitle(titleWithID.getId(), premiumReviewDTO.getPlatformWriterID(), premiumReviewDTO.getSourcePlatform());

            LocalDate currentDate = LocalDate.now();
            Review aPremiumReview = new PremiumReview(premiumReviewDTO.getExtendedDescription(), premiumReviewDTO.getSummaryDescription(),
                    premiumReviewDTO.getRating(), currentDate, premiumReviewDTO.getSourcePlatform(), premiumReviewDTO.getPlatformWriterID(),
                    premiumReviewDTO.getLanguage());

            titleWithID.addReview(aPremiumReview);
            Review savedReview = reviewRepository.save(aPremiumReview);
            titleRepository.save(titleWithID);

            return ResponseEntity.ok().body(savedReview);
        }catch(RepeatedReviewException | InexistentTitleWithIDException | EmptyDTOError e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @Transactional
    public ResponseEntity addNewPublicReview(PublicReviewDTO publicReviewDTO) {

        try{
            publicReviewDTO.assertEmpty();

            Title titleWithID = findTitleByID(publicReviewDTO.getTitleID());

            checkForRepeatedPublicReviewInTitle(titleWithID.getId(), publicReviewDTO.getPlatformWriterID(), publicReviewDTO.getNickName(), publicReviewDTO.getSourcePlatform());

            LocalDate currentDate = LocalDate.now();
            Review aPublicReview = new PublicReview(publicReviewDTO.getExtendedDescription(), publicReviewDTO.getSummaryDescription(),
                    publicReviewDTO.getRating(), publicReviewDTO.getSpoilerAlert(), currentDate, publicReviewDTO.getSourcePlatform(),
                    publicReviewDTO.getPlatformWriterID(), publicReviewDTO.getNickName(), publicReviewDTO.getLanguage(),
                    publicReviewDTO.getGeographicPosition());

            titleWithID.addReview(aPublicReview);
            Review savedReview = reviewRepository.save(aPublicReview);
            titleRepository.save(titleWithID);
            return ResponseEntity.ok().body(savedReview);
        }catch (RepeatedReviewException | InexistentTitleWithIDException | EmptyDTOError  e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @Transactional
    public ResponseEntity likePremiumReview(Integer id) {

        try{
            Review review = findReviewByID(id);
            review.addLike();
            Review savedReview = reviewRepository.save(review);
            return ResponseEntity.ok().body(savedReview);
        }catch(InexistentReviewWithIDException e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @Transactional
    public ResponseEntity disLikePremiumReview(Integer id){

        try{
            Review review = findReviewByID(id);
            review.addDislike();
            Review savedReview = reviewRepository.save(review);
            return ResponseEntity.ok().body(savedReview);
        } catch (InexistentReviewWithIDException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    public ResponseEntity getAllReviewsOfTitleWithID(Integer id){

        try{
            Title titleWithID = findTitleByID(id);

            return ResponseEntity.ok().body(titleWithID.getReviews());
        } catch (InexistentTitleWithIDException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    //-------------------------------------------------------------------------------------------

    private void checkForRepeatedPremiumReviewInTitle(Integer aTitleID, String aPlatformWriterID, String aSourcePlatform) throws RepeatedReviewException {

        Collection<PremiumReview> premiumReviews = reviewRepository.getPremiumReviewsForTitleWithID(aTitleID);

        if(premiumReviews.stream().anyMatch( review -> review.getPlatformWriterID().equals(aPlatformWriterID) &&
                review.getSourcePlatform().equals(aSourcePlatform))){
            throw new RepeatedReviewException("There is already a review in that Title of a writer with that id from that platform");
        }
    }

    private void checkForRepeatedPublicReviewInTitle(Integer aTitleID, String aPlatformWriterID, String aNickName, String aSourcePlatform)
            throws RepeatedReviewException {

        Collection<PublicReview> premiumReviews = reviewRepository.getPublicReviewsForTitleWithID(aTitleID);

        if(premiumReviews.stream().anyMatch( review -> review.getPlatformWriterID().equals(aPlatformWriterID) &&
                review.getNickName().equals(aNickName) &&
                review.getSourcePlatform().equals(aSourcePlatform))){
            throw new RepeatedReviewException("There is already a review in that Title of a writer with that id from that platform");
        }
    }
}

