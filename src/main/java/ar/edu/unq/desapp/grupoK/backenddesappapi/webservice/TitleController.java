package ar.edu.unq.desapp.grupoK.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.FilmWorker;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.PublicReview;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Title;
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.TitleService;
import ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto.FilmWorkerDTO;
import ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto.PublicReviewDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
@EnableAutoConfiguration
public class TitleController {

    @Autowired
    private TitleService titleService;

    @GetMapping("/titles")
    public ResponseEntity allTitles() {

        return ResponseEntity.status(200).body(titleService.findAll());
    }
/*
    @GetMapping("/title")
    public Collection<Title> findByRequest(
                                           @RequestParam(required = false) Integer id,
                                           @RequestParam(required = false) Integer startYear,
                                           @RequestParam(required = false) Integer endYear,
                                           @RequestParam(required = false) Integer runtimeMinutes,
                                           @RequestParam(required = false) String category,
                                           @RequestParam(required = false) Double rating) {


        return titleService.findAllByCriteria(originaTitle, isAnAdultFilm, startYear, endYear, runtimeMinutes, category, rating);

    }
    */
    @GetMapping("/title")
    public List<Title> findByRequest(@RequestBody LinkedHashMap<String, String> filters) throws FileNotFoundException {
        return titleService.findAllByCriteria(filters);
    }




}
