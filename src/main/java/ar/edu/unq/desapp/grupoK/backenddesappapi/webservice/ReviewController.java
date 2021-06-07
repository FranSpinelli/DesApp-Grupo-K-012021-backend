package ar.edu.unq.desapp.grupoK.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.*;
import ar.edu.unq.desapp.grupoK.backenddesappapi.persistence.ReviewRepository;
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.*;
import ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto.PremiumReviewDTO;
import ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto.PublicReviewDTO;
import ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto.ReportDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@EnableAutoConfiguration
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    @Autowired
    private ReportService reportService;
    @Autowired
    private ReviewRepository reviewRepository;

    @PostMapping("/premiumReview")
    @ApiOperation(value = "Post a new Premium Review",
            notes = "Provide a Title id and the Premium Review data",
            response = PremiumReview.class)
    public ResponseEntity addNewPremiumReview(@RequestBody PremiumReviewDTO premiumReviewDTO) {
        return reviewService.addNewPremiumReview(premiumReviewDTO);
    }

    @PostMapping("/publicReview")
    @ApiOperation(value = "Post a new Public Review",
            notes = "Provide a Title id and the Public Review data",
            response = PublicReview.class)
    public ResponseEntity addNewPublicReview(@RequestBody PublicReviewDTO publicReviewDTO) {
        return reviewService.addNewPublicReview(publicReviewDTO);
    }

    @GetMapping("/reviews")
    public ResponseEntity allReviews() {

        return ResponseEntity.status(200).body(reviewService.findAll());
    }

    //-------------------------------------------------------------------------------------

    @GetMapping("/review")
    public Collection<Review> findByRequest(@RequestParam(required = false) String type,
                                            @RequestParam(required = false) Integer id,
                                            @RequestParam(required = false) String source,
                                            @RequestParam(required = false) String language,
                                            @RequestParam(required = false) String country,
                                            @RequestParam(required = false) Boolean spoilerAlert,
                                            @RequestParam(required = false) Double rating,
                                            @RequestParam(required = false, defaultValue = "asc") String orderField,
                                            @RequestParam(required = false, defaultValue = "rating") String criteria) {

        System.out.println(type);
        /*System.out.println(country);
        System.out.println(language);
        System.out.println(orderField);
        System.out.println(spoilerAlert);*/

        //return reviewService.findAll(type, id,source, language, country, spoilerAlert);
        return reviewService.findAll(type, id, source, language, country, spoilerAlert, rating, orderField, criteria);
    }

}


