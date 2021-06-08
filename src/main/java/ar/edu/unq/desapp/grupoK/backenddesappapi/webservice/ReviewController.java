package ar.edu.unq.desapp.grupoK.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.PremiumReview;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.PublicReview;
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.*;
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.serviceLevelExceptions.InexistentElementException;
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.serviceLevelExceptions.RepeatedElementException;
import ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto.EmptyDTOException;
import ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto.PremiumReviewDTO;
import ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto.PublicReviewDTO;
import ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto.ReportDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    @Autowired
    private ReportService reportService;

    @PostMapping("/premiumReview/{apiKey}")
    @ApiOperation(value = "Post a new Premium Review",
                    notes = "Provide a Title id and the Premium Review data",
                    response = PremiumReview.class)
    public ResponseEntity addNewPremiumReview(@RequestBody PremiumReviewDTO premiumReviewDTO) throws EmptyDTOException, InexistentElementException, RepeatedElementException {
        return reviewService.addNewPremiumReview(premiumReviewDTO);
    }

    @PostMapping("/publicReview/{apiKey}")
    @ApiOperation(value = "Post a new Public Review",
                    notes = "Provide a Title id and the Public Review data",
                    response = PublicReview.class)
    public ResponseEntity addNewPublicReview(@RequestBody PublicReviewDTO publicReviewDTO) throws EmptyDTOException, InexistentElementException, RepeatedElementException {
        return reviewService.addNewPublicReview(publicReviewDTO);
    }

    @PutMapping("/review/like/{apiKey}")
    @ApiOperation(value = "Like Review",
                    notes = "Provide the Review id you want to like",
                    response = Integer.class)
    public ResponseEntity likePremiumReview(@RequestParam Integer id) throws InexistentElementException {
        return reviewService.likePremiumReview(id);
    }

    @PutMapping("/review/dislike/{apiKey}")
    @ApiOperation(value = "Dislike Review",
                    notes = "Provide the Review id you want to dislike",
                    response = Integer.class)
    public ResponseEntity disLikePremiumReview(@RequestParam Integer id) throws InexistentElementException {
        return reviewService.disLikePremiumReview(id);
    }

    @PostMapping("/review/report/{apiKey}")
    public ResponseEntity reportReview(@RequestBody ReportDTO reportDTO) throws EmptyDTOException, InexistentElementException, RepeatedElementException {
        return reportService.addNewReport(reportDTO);
    }

    @GetMapping(value = "/review/{id}/{apiKey}")
    @ApiOperation(value = "Get all the reviews of a specific Title",
                    notes = "Provide the Title id in order to get its reviews")
    public ResponseEntity getAllReviews(@ApiParam(value = "ID value for the Title's reviews you are interested in", required = true)
                                            @PathVariable("id") Integer id) throws InexistentElementException {
        return reviewService.getAllReviewsOfTitleWithID(id);
    }
}
