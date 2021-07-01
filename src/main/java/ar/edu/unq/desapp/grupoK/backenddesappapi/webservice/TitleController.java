package ar.edu.unq.desapp.grupoK.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.TitleGenericInfo;
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.TitleService;
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.serviceLevelExceptions.InexistentElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class TitleController {

    @Autowired
    private TitleService titleService;

    @GetMapping("/titles")
    public ResponseEntity allTitles() {

        return titleService.findAll();
    }

    @GetMapping("/titleData/{id}")
    public ResponseEntity getTitleGenericData(@PathVariable Integer id) throws InexistentElementException {

        return ResponseEntity.ok().body(titleService.getTitleGenericData(id));
    }
}
