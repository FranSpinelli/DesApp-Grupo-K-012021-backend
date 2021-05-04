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
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    @ApiOperation(value = "Post a new Premium Review",
                    notes = "Provide a Title id and the Premium Review data",
                    response = PremiumReview.class)
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
    @ApiOperation(value = "Post a new Public Review",
            notes = "Provide a Title id and the Public Review data",
            response = PublicReview.class)
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
    @ApiOperation(value = "Like Review",
            notes = "Provide the Review id you want to like",
            response = Integer.class)
    public ResponseEntity likePremiumReview(@RequestParam Integer id) {

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
    @ApiOperation(value = "Dislike Review",
            notes = "Provide the Review id you want to dislike",
            response = Integer.class)
    public ResponseEntity disLikePremiumReview(@RequestParam Integer id){

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

    @GetMapping(value = "/review/{id}")
    @ApiOperation(value = "Get all the reviews of a specific Title",
            notes = "Provide the Title id in order to get its reviews")
    public ResponseEntity getAllReviews(@ApiParam(value = "ID value for the Title's reviews you are interested in", required = true)
                                            @PathVariable("id") Integer id){

        try{
            Title titleWithID = this.titleService.findByID(id);
            List<PremiumReview> reviews = titleWithID.getReviews();

            return ResponseEntity.status(200).body(reviews);

        } catch(InexistentFilmWithIDError e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
