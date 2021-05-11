package ar.edu.unq.desapp.grupoK.backenddesappapi.service;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.PremiumReview;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.PublicReview;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Title;
import ar.edu.unq.desapp.grupoK.backenddesappapi.persistence.ReviewRepository;
import ar.edu.unq.desapp.grupoK.backenddesappapi.persistence.TitleRepository;
import ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto.EmptyDTOError;
import ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto.PremiumReviewDTO;
import ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto.PublicReviewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private TitleRepository titleRepository;

    @Transactional
    public ResponseEntity addNewPremiumReview(PremiumReviewDTO premiumReviewDTO) {

        try {
            premiumReviewDTO.assertEmpty();

            Title titleWithID = findTitleByID(premiumReviewDTO.getTitleID());

            checkForRepeatedPremiumReviewInTitle(titleWithID, premiumReviewDTO.getPlatformWriterID(), premiumReviewDTO.getSourcePlatform());

            LocalDate currentDate = LocalDate.now();
            PremiumReview aPremiumReview = new PremiumReview(premiumReviewDTO.getExtendedDescription(), premiumReviewDTO.getSummaryDescription(),
                    premiumReviewDTO.getRating(), currentDate, premiumReviewDTO.getSourcePlatform(), premiumReviewDTO.getPlatformWriterID(),
                    premiumReviewDTO.getLanguage());

            titleWithID.addReview(aPremiumReview);
            PremiumReview savedReview = reviewRepository.save(aPremiumReview);
            titleRepository.save(titleWithID);

            return ResponseEntity.ok().body(savedReview);
        }catch(RepeatedReviewException | InexistentTitleWithIDError | EmptyDTOError e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @Transactional
    public ResponseEntity addNewPublicReview(PublicReviewDTO publicReviewDTO) {

        try{
            publicReviewDTO.assertEmpty();

            Title titleWithID = findTitleByID(publicReviewDTO.getTitleID());

            checkForRepeatedPublicReviewInTitle(titleWithID, publicReviewDTO.getPlatformWriterID(), publicReviewDTO.getNickName(), publicReviewDTO.getSourcePlatform());

            LocalDate currentDate = LocalDate.now();
            PremiumReview aPublicReview = new PublicReview(publicReviewDTO.getExtendedDescription(), publicReviewDTO.getSummaryDescription(),
                    publicReviewDTO.getRating(), publicReviewDTO.getSpoilerAlert(), currentDate, publicReviewDTO.getSourcePlatform(),
                    publicReviewDTO.getPlatformWriterID(), publicReviewDTO.getNickName(), publicReviewDTO.getLanguage(),
                    publicReviewDTO.getGeographicPosition());

            titleWithID.addReview(aPublicReview);
            PremiumReview savedReview = reviewRepository.save(aPublicReview);
            titleRepository.save(titleWithID);
            return ResponseEntity.ok().body(savedReview);
        }catch (RepeatedReviewException | InexistentTitleWithIDError | EmptyDTOError  e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @Transactional
    public ResponseEntity likePremiumReview(Integer id) {

        try{
            PremiumReview review = findReviewByID(id);
            review.addLike();
            PremiumReview savedReview = reviewRepository.save(review);
            return ResponseEntity.ok().body(savedReview);
        }catch(InexistentReviewWithIDError e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @Transactional
    public ResponseEntity disLikePremiumReview(Integer id){

        try{
            PremiumReview review = findReviewByID(id);
            review.addDislike();
            PremiumReview savedReview = reviewRepository.save(review);
            return ResponseEntity.ok().body(savedReview);
        } catch (InexistentReviewWithIDError e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    public ResponseEntity getAllReviewsOfTitleWithID(Integer id){

        try{
            Title titleWithID = findTitleByID(id);

            return ResponseEntity.ok().body(titleWithID.getReviews());
        } catch (InexistentTitleWithIDError e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    //-------------------------------------------------------------------------------------------

    private void checkForRepeatedPremiumReviewInTitle(Title aTitle, String aPlatformWriterID, String aSourcePlatform) throws RepeatedReviewException {

        if(aTitle.getReviews().stream()
                .anyMatch(review -> review.getPlatformWriterID().equals(aPlatformWriterID) &&
                review.getSourcePlatform().equals(aSourcePlatform))){
            throw new RepeatedReviewException("There is already a review in that Title of a writer with that id from that platform");
        }
    }

    private void checkForRepeatedPublicReviewInTitle(Title aTitle, String aPlatformWriterID, String aNickName, String aSourcePlatform)
            throws RepeatedReviewException {

        if(aTitle.getReviews().stream().anyMatch(review -> review.getPlatformWriterID().equals(aPlatformWriterID) &&
                review.getNickName().equals(aNickName) &&
                review.getSourcePlatform().equals(aSourcePlatform))) {
            throw new RepeatedReviewException("There is already a review in that Title of a writer with that id from that platform");
        }
    }

    private PremiumReview findReviewByID(Integer id) throws InexistentReviewWithIDError {
        try{
            return this.reviewRepository.findById(id).get();
        }catch(NoSuchElementException e){
            throw new InexistentReviewWithIDError("There is no Review with id: " + id);
        }
    }

    private Title findTitleByID(Integer id) throws InexistentTitleWithIDError {

        try{
            return titleRepository.findById(id).get();
        }catch(NoSuchElementException e){
            throw new InexistentTitleWithIDError("There is no Review with id: " + id);
        }
    }
}

