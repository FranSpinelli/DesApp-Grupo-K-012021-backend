package ar.edu.unq.desapp.grupoK.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Title;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.TitleService;

import java.util.List;

@RestController
@EnableAutoConfiguration
public class TitleController {

    @Autowired
    private TitleService titleService;

    @GetMapping("/titles/{apiKey}")
    public ResponseEntity allTitles() {

        return ResponseEntity.status(200).body(titleService.findAll());
    }
}
