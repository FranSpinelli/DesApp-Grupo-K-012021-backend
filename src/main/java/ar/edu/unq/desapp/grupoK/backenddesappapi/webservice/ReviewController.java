package ar.edu.unq.desapp.grupoK.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.PremiumReview;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.PublicReview;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Title;
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.InexistentFilmWithIDError;
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.InexistentReviewWithIDError;
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.ReviewService;
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.TitleService;
import ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto.EmptyDTOError;
import ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto.PremiumReviewDTO;
import ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto.PublicReviewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@EnableAutoConfiguration
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    @Autowired
    private TitleService titleService;

    @PostMapping("/premiumReview")
    public ResponseEntity addNewPremiumReview(@RequestBody PremiumReviewDTO premiumReviewDTO){

        try {
            premiumReviewDTO.assertEmpty();

            List<PremiumReview> list = reviewService.findAllPremiumReviews();
            if(list.stream().anyMatch(review -> review.getPlatformWriterID().equals(premiumReviewDTO.getPlatformWriterID()) &&
                                    review.getSourcePlatform().equals(premiumReviewDTO.getSourcePlatform()))){
                throw new RepeatedReviewException("There is already a review of a writer with that id from that platform");
            }

            LocalDate currentDate = LocalDate.now();
            PremiumReview aPremiumReview = new PremiumReview(premiumReviewDTO.getExtendedDescription(), premiumReviewDTO.getSummaryDescription(),
                    premiumReviewDTO.getRating(), currentDate, premiumReviewDTO.getSourcePlatform(), premiumReviewDTO.getPlatformWriterID(),
                    premiumReviewDTO.getLenguage());
            Title titleWithID = this.titleService.findByID(premiumReviewDTO.getTitleID());
            titleWithID.addReview(aPremiumReview);
            PremiumReview saved = reviewService.save(aPremiumReview);
            titleService.save(titleWithID);
            return ResponseEntity.status(200).body(saved);
        }catch(InexistentFilmWithIDError | EmptyDTOError | RepeatedReviewException e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping("/publicReview")
    public ResponseEntity addNewPublicReview(@RequestBody PublicReviewDTO publicReviewDTO){

        try {
            publicReviewDTO.assertEmpty();

            List<PublicReview> list = reviewService.findAllPublicReviews();
            if(list.stream().anyMatch(review -> review.getPlatformWriterID().equals(publicReviewDTO.getPlatformWriterID()) &&
                    review.getNickName().equals(publicReviewDTO.getNickName()) &&
                    review.getSourcePlatform().equals(publicReviewDTO.getSourcePlatform()))){
                throw new RepeatedReviewException("There is already a review of a writer with that id from that platform");
            }

            LocalDate currentDate = LocalDate.now();
            PremiumReview aPublicReview = new PublicReview(publicReviewDTO.getExtendedDescription(), publicReviewDTO.getSummaryDescription(),
                    publicReviewDTO.getRating(), publicReviewDTO.getSpoilerAlert(), currentDate, publicReviewDTO.getSourcePlatform(),
                    publicReviewDTO.getPlatformWriterID(), publicReviewDTO.getNickName(), publicReviewDTO.getLenguage(),
                    publicReviewDTO.getGeographicPosition());
            Title titleWithID = this.titleService.findByID(publicReviewDTO.getTitleID());
            titleWithID.addReview(aPublicReview);
            PremiumReview saved = reviewService.save(aPublicReview);
            titleService.save(titleWithID);
            return ResponseEntity.status(200).body(saved);
        }catch(InexistentFilmWithIDError | EmptyDTOError | RepeatedReviewException e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PutMapping("/review/like")
    public ResponseEntity likePremiumReview(@RequestParam Integer id) {  //id de la review que se quiere darle like

        try {
            PremiumReview review = reviewService.findByID(id);
            review.addLike();
            PremiumReview saved = reviewService.save(review);
            return ResponseEntity.status(200).body(saved);

        } catch (InexistentReviewWithIDError e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PutMapping("/review/dislike")
    public ResponseEntity disLikePremiumReview(@RequestParam Integer id){  //id de la review que se quiere darle like

        try{
            PremiumReview review = reviewService.findByID(id);
            review.addDislike();
            PremiumReview saved = reviewService.save(review);
            return ResponseEntity.status(200).body(saved);

        }
        catch(InexistentReviewWithIDError e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getAllReviews(@PathVariable("id") Integer id){  //id del title

        try{
            Title titleWithID = this.titleService.findByID(id);
            List<PremiumReview> reviews = titleWithID.getReviews();

            return ResponseEntity.status(200).body(reviews);

        } catch(InexistentFilmWithIDError e){
            return ResponseEntity.status(400).body(e.getMessage());
        }

    }
}
