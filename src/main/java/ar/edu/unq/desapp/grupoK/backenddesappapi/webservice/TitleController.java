package ar.edu.unq.desapp.grupoK.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Title;
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@EnableAutoConfiguration
public class TitleController {

    @Autowired
    private TitleService titleService;

    /*@GetMapping("/titles")
    public ResponseEntity allTitles() {

        return ResponseEntity.status(200).body(titleService.findAll());
    }*/

    @GetMapping("/title")
    public Collection<Title> findByRequest(
                                           @RequestParam(required = false) String originaTitle,
                                           @RequestParam(required = false) Boolean isAnAdultFilm,
                                           @RequestParam(required = false) Integer startYear,
                                           @RequestParam(required = false) Integer endYear,
                                           @RequestParam(required = false) Integer runtimeMinutes,
                                           @RequestParam(required = false) String category,
                                           @RequestParam(required = false) Double rating) {


        return titleService.findAll(originaTitle, isAnAdultFilm, startYear, endYear, runtimeMinutes, category, rating);

    }





}
