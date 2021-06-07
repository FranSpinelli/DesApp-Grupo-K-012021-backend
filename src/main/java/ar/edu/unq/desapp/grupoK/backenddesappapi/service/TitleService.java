package ar.edu.unq.desapp.grupoK.backenddesappapi.service;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Review;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Title;
import ar.edu.unq.desapp.grupoK.backenddesappapi.persistence.TitleRepository;
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.serviceLevelExceptions.InexistentTitleWithIDError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TitleService {

    @Autowired
    private TitleRepository titleRepository;

    @Transactional
    public Title save(Title model) {
        return this.titleRepository.save(model);
    }

    public Title findByID(Integer id) throws InexistentTitleWithIDError {
        try{
            return this.titleRepository.findById(id).get();
        }catch(NoSuchElementException e){
            throw new InexistentTitleWithIDError("There is no Film with id: " + id);
        }
    }

    public Title findTitleById(Integer id) {return this.titleRepository.findById(id).get();}

    //public List<Title> findAll() {
    //    return this.titleRepository.findAll();
    //}

    public Collection<Title> findAll(String originalTitle,
                                     Boolean isAnAdultFilm,
                                     Integer startYear,
                                     Integer endYear,
                                     Integer runtimeMinutes,
                                     String category,
                                     Double rating) {



        //List<Review> titleReviews = findTitleById(id).getReviews();

        //ArrayList<Review> reviews = (ArrayList<Review>) titleReviews.stream().filter(r -> r.getRating().equals(rating)).collect(Collectors.toList());

        Title title = new Title();
/*
        if(reviews.size() != 0){
            for(Review review : reviews){
                title.addReview(review);
            }
        }*/

        System.out.println(endYear);
        title.setCategory(category);
        title.setEndYear(endYear);
        title.setStartYear(startYear);
        title.setOriginalTitle(originalTitle);
        title.setIsAnAdultFilmIndicator(isAnAdultFilm);
        title.setRuntimeMinutes(runtimeMinutes);
        System.out.println(title.getEndYear());



        Example<Title> example = Example.of(title);



        return titleRepository.findAll(example);
    }


}

