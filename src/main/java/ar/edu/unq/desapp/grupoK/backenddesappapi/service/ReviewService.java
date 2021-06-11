package ar.edu.unq.desapp.grupoK.backenddesappapi.service;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.*;
import ar.edu.unq.desapp.grupoK.backenddesappapi.persistence.ReviewRepository;
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.serviceLevelExceptions.*;
import ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto.EmptyDTOError;
import ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto.PremiumReviewDTO;
import ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto.PublicReviewDTO;
import org.springframework.beans.ConfigurablePropertyAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.LocalDate;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ReviewService extends AbstractService {

    @Autowired
    private ReviewRepository repository;

     @Transactional
    public ResponseEntity addNewPremiumReview(PremiumReviewDTO premiumReviewDTO) {

        try {
            premiumReviewDTO.assertEmpty();

            Title titleWithID = findTitleByID(premiumReviewDTO.getTitleID());

            checkForRepeatedPremiumReviewInTitle(titleWithID.getId(), premiumReviewDTO.getPlatformWriterID(), premiumReviewDTO.getSourcePlatform());

            LocalDate currentDate = LocalDate.now();
            Review aPremiumReview = new PremiumReview(premiumReviewDTO.getExtendedDescription(), premiumReviewDTO.getSummaryDescription(),
                    premiumReviewDTO.getRating(), currentDate, premiumReviewDTO.getSourcePlatform(), premiumReviewDTO.getPlatformWriterID(),
                    premiumReviewDTO.getLanguage(), premiumReviewDTO.getType());

            titleWithID.addReview(aPremiumReview);
            Review savedReview = reviewRepository.save(aPremiumReview);
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

            checkForRepeatedPublicReviewInTitle(titleWithID.getId(), publicReviewDTO.getPlatformWriterID(), publicReviewDTO.getNickName(), publicReviewDTO.getSourcePlatform());

            LocalDate currentDate = LocalDate.now();
            Review aPublicReview = new PublicReview(publicReviewDTO.getExtendedDescription(), publicReviewDTO.getSummaryDescription(),
                    publicReviewDTO.getRating(), publicReviewDTO.getSpoilerAlert(), currentDate, publicReviewDTO.getSourcePlatform(),
                    publicReviewDTO.getPlatformWriterID(), publicReviewDTO.getNickName(), publicReviewDTO.getLanguage(),
                    publicReviewDTO.getGeographicPosition(), publicReviewDTO.getType());

            titleWithID.addReview(aPublicReview);
            Review savedReview = reviewRepository.save(aPublicReview);
            titleRepository.save(titleWithID);
            return ResponseEntity.ok().body(savedReview);
        }catch (RepeatedReviewException | InexistentTitleWithIDError | EmptyDTOError  e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @Transactional
    public ResponseEntity likePremiumReview(Integer id) {

        try{
            Review review = findReviewByID(id);
            review.addLike();
            Review savedReview = reviewRepository.save(review);
            return ResponseEntity.ok().body(savedReview);
        }catch(InexistentReviewWithIDError e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @Transactional
    public ResponseEntity disLikePremiumReview(Integer id){
    System.out.println("el id es: " + id);
        try{
            Review review = findReviewByID(id);
            review.addDislike();
            Review savedReview = reviewRepository.save(review);
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

    public List<Review> findAll() {
        return this.repository.findAll();
    }

   //-------------------------------------------------------------------------------------------

    private void checkForRepeatedPremiumReviewInTitle(Integer aTitleID, String aPlatformWriterID, String aSourcePlatform) throws RepeatedReviewException {

        Collection<PremiumReview> premiumReviews = reviewRepository.getPremiumReviewsForTitleWithID(aTitleID);

        if(premiumReviews.stream().anyMatch( review -> review.getPlatformWriterID().equals(aPlatformWriterID) &&
                review.getSourcePlatform().equals(aSourcePlatform))){
            throw new RepeatedReviewException("There is already a review in that Title of a writer with that id from that platform");
        }
    }

    private void checkForRepeatedPublicReviewInTitle(Integer aTitleID, String aPlatformWriterID, String aNickName, String aSourcePlatform)
            throws RepeatedReviewException {

        Collection<PublicReview> premiumReviews = reviewRepository.getPublicReviewsForTitleWithID(aTitleID);

        if(premiumReviews.stream().anyMatch( review -> review.getPlatformWriterID().equals(aPlatformWriterID) &&
                review.getNickName().equals(aNickName) &&
                review.getSourcePlatform().equals(aSourcePlatform))){
            throw new RepeatedReviewException("There is already a review in that Title of a writer with that id from that platform");
        }
    }

    public Collection<Review> findAll(String type,
                                      Integer id,
                                      String source,
                                      String language,
                                      String country,
                                      Boolean spoilerAlert,
                                      Double rating,
                                      String orderBy,
                                      String criteria) {
/*
        Sort.Direction sorting;

        if (criteria.toLowerCase() == "asc") {
            sorting = Sort.Direction.ASC;
        } else {
            sorting = Sort.Direction.DESC;
        }

        if (orderBy == "rating") {
            orderBy = "rating" ;
        } else {
            orderBy = "date" ;
        }
*/
        if (Objects.equals(type, "pubReview")) {
            System.out.println(type);
            PublicReview pubReview = new PublicReview();
            pubReview.setTypeReview(type);
            pubReview.setSpoilerAlert(spoilerAlert);
            pubReview.setGeographicPosition(country);
            pubReview.setRating(rating);
            pubReview.setLanguage(language);
            pubReview.setSourcePlatform(source);
            Example<Review> examplePubl = Example.of(pubReview);
            Collection<Review> publicReview = reviewRepository.findAll(examplePubl);
            //return reviewRepository.findAll(examplePubl, Sort.by(sorting, orderBy));
            return publicReview;

        }else if (Objects.equals(type, "premReview")) {
            PremiumReview premReview = new PremiumReview();
            premReview.setTypeReview(type);
            premReview.setRating(rating);
            premReview.setLanguage(language);
            premReview.setSourcePlatform(source);
            Example<Review> examplePrem = Example.of(premReview);
            //Collection<Review> premiumReview = reviewRepository.findAll(examplePrem,Sort.by(sorting, orderBy));
            Collection<Review> premiumReview = reviewRepository.findAll(examplePrem);
            return premiumReview;

        }else if (spoilerAlert != null || country != null) {
            PublicReview pubReview = new PublicReview();
            pubReview.setTypeReview(type);
            pubReview.setGeographicPosition(country);
            pubReview.setSpoilerAlert(spoilerAlert);
            pubReview.setRating(rating);
            pubReview.setLanguage(language);
            pubReview.setSourcePlatform(source);
            Example<Review> examplePubl = Example.of(pubReview);
            Collection<Review> publicReviews = reviewRepository.findAll(examplePubl);
            //return reviewRepository.findAll(examplePubl, Sort.by(sorting, orderBy));
            return publicReviews;
        }else {

            PremiumReview premReview = new PremiumReview();
            premReview.setRating(rating);
            premReview.setLanguage(language);
            premReview.setSourcePlatform(source);

            PublicReview pubReview = new PublicReview();
            pubReview.setGeographicPosition(country);
            pubReview.setSpoilerAlert(spoilerAlert);
            pubReview.setRating(rating);
            pubReview.setLanguage(language);
            pubReview.setSourcePlatform(source);

            Example<Review> examplePubl = Example.of(pubReview);
            Example<Review> examplePrem = Example.of(premReview);
            //Collection<Review> premiumReview = reviewRepository.findAll(examplePrem, Sort.by(sorting, orderBy));
            //Collection<Review> publicReview = reviewRepository.findAll(examplePubl, Sort.by(sorting, orderBy));
            Collection<Review> premiumReviews = reviewRepository.findAll(examplePrem);
            Collection<Review> publicReviews = reviewRepository.findAll(examplePubl);

            Collection<Review> collectionReviews = Stream.concat(premiumReviews.stream(), publicReviews.stream()).collect(Collectors.toList());

            return collectionReviews;
        }
/*
        if (orderBy == "rating" && criteria == "asc") {
            //return Stream.concat(premiumReview.stream(), publicReview.stream()).sorted(Comparator.comparing(Review::getRating)).collect(Collectors.toList());
            Collection<Review> collectionOrderBy = collectionReview
        }
        if (orderBy == "rating" && criteria.toLowerCase() == "dec") {
            return Stream.concat(premiumReview.stream(), publicReview.stream()).sorted(Comparator.comparing(Review::getRating).reversed()).collect(Collectors.toList());
        }
        if (orderBy == "date" && criteria == "asc") {
            return Stream.concat(premiumReview.stream(), publicReview.stream()).sorted(Comparator.comparing(Review::getDate)).collect(Collectors.toList());
        }
        if (orderBy == "date" && criteria == "dec") {
            return Stream.concat(premiumReview.stream(), publicReview.stream()).sorted(Comparator.comparing(Review::getDate).reversed()).collect(Collectors.toList());
        }
*/


    }



}



        //return reviewRepository.findAll(example, Sort.by(sorting, orderBy));
        //return reviewRepository.findAll(example);




       // Example<Review> example = Example.of(review);
        //Collection<Review> results = reviewRepository.findAll(example);


        // Collection<Review> results =
        //Collection<Review>  .-> lista de todos los filtros por cada campo de la query, por lo tanto
        // varias valriables pasan por el filtro
        // pasar por un set y me fijo que no haya  repetidos en lo que me trae
        //antes concateno todo
        // una vez que no haya repetidos lo paso una lista
        //y eso me sirve para el example , no lo necesito mas
        //no mas example, ni public review
        // en el repository sería "Collection<Review> findAll(Example<Review> example);"
       // return results;



/*
    Comparator<Persona> comparadorMultiple= Comparator.comparing(Persona::getNombre).thenComparing(Comparator.comparing(Persona::getApellidos)).thenComparing(Comparator.comparing(Persona::getEdad));
    lista.stream().sorted(comparadorMultiple).forEach(System.out::println);

        lista.stream().sorted(Comparator.comparing(Persona::getApellidos)).forEach(System.out::println);

        public Collection<Review> findReviewsWithPlatform (String id){

            Collection<Review> reviewsWithPlatform = reviewRepository.findReviewsWithPlatform(id);
            return reviewsWithPlatform ;
        }

        //Collection<Review> reviewsWithPlatform = reviewRepository.findReviewsWithPlatform(id);
        //return reviewsWithPlatform ;



    public Collection<Review> findReviewsByTypePremium(String type){
        Collection<Review> reviewsWithTypePremium = reviewRepository.findReviewsByTypeReview(type);
        return reviewsWithTypePremium;
    }

    public Collection<Review> findReviewsBySpoilerAlert(Boolean spoilerAlert){
        Collection<Review> reviewsWithSpoilerAlert = reviewRepository.findReviewsBySpoilerAlert(spoilerAlert);
        return reviewsWithSpoilerAlert;
    }

    public Collection<Review> findReviewsByType(String type){
        Collection<Review> reviewsWithType = reviewRepository.findReviewsByTypeReview(type);
        return reviewsWithType;
    }

    public Collection<Review> findREviewsByLanguage(String language){
        Collection<Review> reviewsWithLanguage = reviewRepository.findReviewsByLanguage(language);
        return reviewsWithLanguage;
    }

    public Collection<Review> findReviewsByCountry(String country){
        Collection<Review> reviewsWithCountry = reviewRepository.findReviewsByCountry(country);
        return reviewsWithCountry;
    }

    public Collection<Review> orderReviewsByRatingDec(){
        Collection<Review> reviewsOrderDecByRating = reviewRepository.orderReviewsByRatingDec();
        return reviewsOrderDecByRating;
    }




    /*public Collection<Review> findPlatformFromReview(Map<String, String> filters){
        Review publicReview = new PublicReview();

        publicReview.setPlatformWriterID(filters.get("platform"));

        Example<Review> example = Example.of(publicReview);
        Collection<Review> results = reviewRepository.findPlatform(example);

        return results;
    }*/





