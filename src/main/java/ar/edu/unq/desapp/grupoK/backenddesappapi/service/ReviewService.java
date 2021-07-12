package ar.edu.unq.desapp.grupoK.backenddesappapi.service;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.*;
import ar.edu.unq.desapp.grupoK.backenddesappapi.persistence.ReviewRepository;
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.serviceLevelExceptions.*;
import ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto.EmptyDTOError;
import ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto.PremiumReviewDTO;
import ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto.PublicReviewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.time.LocalDate;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ReviewService extends AbstractService {

    @Autowired
    private ReviewRepository repository;


    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public ResponseEntity addNewPremiumReview(PremiumReviewDTO premiumReviewDTO) {

        try {
            premiumReviewDTO.assertEmpty();

            Title titleWithID = findTitleByID(premiumReviewDTO.getTitleID());

            checkForRepeatedPremiumReviewInTitle(titleWithID.getId(), premiumReviewDTO.getPlatformWriterID(), premiumReviewDTO.getSourcePlatform());

            LocalDate currentDate = LocalDate.now();
            Review aPremiumReview = new PremiumReview(premiumReviewDTO.getExtendedDescription(), premiumReviewDTO.getSummaryDescription(),
                    premiumReviewDTO.getRating(), currentDate, premiumReviewDTO.getSourcePlatform(), premiumReviewDTO.getPlatformWriterID(),
                    premiumReviewDTO.getLanguage(), premiumReviewDTO.getType(), premiumReviewDTO.getTitleID());

            titleWithID.addReview(aPremiumReview);
            Review savedReview = reviewRepository.save(aPremiumReview);
            titleWithID.setStars();
            titleRepository.save(titleWithID);

            return ResponseEntity.ok().body(savedReview);
        } catch (RepeatedReviewException | InexistentTitleWithIDError | EmptyDTOError e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @Transactional
    public ResponseEntity addNewPublicReview(PublicReviewDTO publicReviewDTO) {

        try {
            publicReviewDTO.assertEmpty();

            Title titleWithID = findTitleByID(publicReviewDTO.getTitleID());

            checkForRepeatedPublicReviewInTitle(titleWithID.getId(), publicReviewDTO.getPlatformWriterID(), publicReviewDTO.getNickName(), publicReviewDTO.getSourcePlatform());

            LocalDate currentDate = LocalDate.now();
            Review aPublicReview = new PublicReview(publicReviewDTO.getExtendedDescription(), publicReviewDTO.getSummaryDescription(),
                    publicReviewDTO.getRating(), publicReviewDTO.getSpoilerAlert(), currentDate, publicReviewDTO.getSourcePlatform(),
                    publicReviewDTO.getPlatformWriterID(), publicReviewDTO.getNickName(), publicReviewDTO.getLanguage(),
                    publicReviewDTO.getGeographicPosition(), publicReviewDTO.getType(), publicReviewDTO.getTitleID());

            titleWithID.addReview(aPublicReview);
            Review savedReview = reviewRepository.save(aPublicReview);
            titleWithID.setStars();
            titleRepository.save(titleWithID);

            return ResponseEntity.ok().body(savedReview);
        } catch (RepeatedReviewException | InexistentTitleWithIDError | EmptyDTOError e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @Transactional
    public ResponseEntity likePremiumReview(Integer id) {

        try {
            Review review = findReviewByID(id);
            review.addLike();
            Review savedReview = reviewRepository.save(review);
            return ResponseEntity.ok().body(savedReview);
        } catch (InexistentReviewWithIDError e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @Transactional
    public ResponseEntity disLikePremiumReview(Integer id) {
        System.out.println("el id es: " + id);
        try {
            Review review = findReviewByID(id);
            review.addDislike();
            Review savedReview = reviewRepository.save(review);
            return ResponseEntity.ok().body(savedReview);
        } catch (InexistentReviewWithIDError e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    public ResponseEntity getAllReviewsOfTitleWithID(Integer id) {

        try {
            Title titleWithID = findTitleByID(id);

            return ResponseEntity.ok().body(titleWithID.getReviews());
        } catch (InexistentTitleWithIDError e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    public List<Review> findAllReviews() {
        return this.repository.findAll();
    }

    //-------------------------------------------------------------------------------------------

    private void checkForRepeatedPremiumReviewInTitle(Integer aTitleID, String aPlatformWriterID, String aSourcePlatform) throws RepeatedReviewException {

        Collection<PremiumReview> premiumReviews = reviewRepository.getPremiumReviewsForTitleWithID(aTitleID);

        if (premiumReviews.stream().anyMatch(review -> review.getPlatformWriterID().equals(aPlatformWriterID) &&
                review.getSource_platform().equals(aSourcePlatform))) {
            throw new RepeatedReviewException("There is already a review in that Title of a writer with that id from that platform");
        }
    }

    private void checkForRepeatedPublicReviewInTitle(Integer aTitleID, String aPlatformWriterID, String aNickName, String aSourcePlatform)
            throws RepeatedReviewException {

        Collection<PublicReview> premiumReviews = reviewRepository.getPublicReviewsForTitleWithID(aTitleID);

        if (premiumReviews.stream().anyMatch(review -> review.getPlatformWriterID().equals(aPlatformWriterID) &&
                review.getNickName().equals(aNickName) &&
                review.getSource_platform().equals(aSourcePlatform))) {
            throw new RepeatedReviewException("There is already a review in that Title of a writer with that id from that platform");
        }
    }






    /*public List<Review> findAllByCriteria(String type,
                                          Integer id,
                                          String source,
                                          String language,
                                          String country,
                                          Boolean spoilerAlert,
                                          Double rating,
                                          String orderBy,
                                          String criteria){*/
/*
    public List<Review> findAllByCriteria(LinkedHashMap<String, String> fields){



        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Review> query= cb.createQuery(Review.class);
        CriteriaQuery<PublicReview> query1 = cb.createQuery(PublicReview.class);
        Root<Review> root = query.from(Review.class);

        Root<PublicReview> rootPubReview = query.from(PublicReview.class);

        List<Predicate> predicates = new ArrayList<>();
        Root<PublicReview> rootPub = query.from(PublicReview.class);



       // String spoiler_alert = fields.get("spoiler_alert");

        //String geographic_position = fields.get("geographic_position");



        //if (Objects.nonNull("spoiler_alert") || Objects.nonNull("geographic_position") ){
        //if(spoiler_alert != null || geographic_position != null){
            fields.keySet().forEach(key ->
            {switch (key) {
                case "id":
                    predicates.add(cb.equal(root.get(key), fields.get(key)));
                    break;
                case "type":
                    predicates.add(cb.equal(root.get(key), fields.get(key)));
                    break;
                case "source_platform":
                    predicates.add(cb.equal(root.get(key), fields.get(key)));
                    break;
                case "language":
                    predicates.add(cb.equal(root.get(key), fields.get(key)));
                    break;
                case "geographic_position":
                    predicates.add(cb.equal(rootPubReview.get(key), fields.get(key)));
                    break;
                case "spoiler_alert":
                    predicates.add(cb.equal(rootPubReview.get(key), fields.get(key)));
                    break;
                case "rating":
                    predicates.add(cb.equal(root.get(key), fields.get(key)));
                    break;
                case "date":
                    predicates.add(cb.equal(root.get(key), fields.get(key)));
                    break;
            }
            });
        if (fields.get("orderBy") != null) {
            switch (fields.get("orderBy")) {
                case "ratingAsc":
                    query.orderBy(cb.asc(root.get("rating"))).where(predicates.toArray(new Predicate[0]));
                    break;
                case "ratingDesc":
                    query.orderBy(cb.desc(root.get("rating"))).where(predicates.toArray(new Predicate[0]));
                    break;
                case "dateAsc":
                    query.orderBy(cb.asc(root.get("date"))).where(predicates.toArray(new Predicate[0]));
                    break;
                case "dateDesc":
                    query.orderBy(cb.desc(root.get("date"))).where(predicates.toArray(new Predicate[0]));
                    break;
            }
        } else {
            query.where(predicates.toArray(new Predicate[0]));
        }


        return entityManager.createQuery(query).getResultList();


       /* }else{
            fields.keySet().forEach(key ->
            {switch (key) {
                case "id":
                    predicates.add(cb.equal(root.get(key), fields.get(key)));
                    break;
                case "type":
                    predicates.add(cb.equal(root.get(key), fields.get(key)));
                    break;
                case "source_platform":
                    predicates.add(cb.equal(root.get(key), fields.get(key)));
                    break;
                case "language":
                    predicates.add(cb.equal(root.get(key), fields.get(key)));
                    break;
                case "rating":
                    predicates.add(cb.equal(root.get(key), fields.get(key)));
                    break;
                case "date":
                    predicates.add(cb.equal(root.get(key), fields.get(key)));
                    break;
            }
            });
        }*/



/*
        fields.keySet().forEach(key ->
        {switch (key) {
            case "geographic_position":
                predicatesReviewPublic.add(cb.equal(rootPub.get(key), fields.get(key)));
                break;
            case "spoiler_alert":
                predicatesReviewPublic.add(cb.equal(rootPub.get(key), fields.get(key)));
                break;
            }
        });*/






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
        }*/


    public Collection<Review> findAllByCriteria(String type,
                                                Integer id,
                                                String source,
                                                String language,
                                                String country,
                                                Boolean spoilerAlert,
                                                Double rating,
                                                String orderBy,
                                                String criterio) {

        Sort.Direction sorting;

        if (orderBy.toLowerCase() == "asc") {
            sorting = Sort.Direction.ASC;
            System.out.println("sorting: "+ sorting);
        } else {
            sorting = Sort.Direction.DESC;
        }

        if (criterio == "rating") {
            criterio = "rating";
        } else {
            criterio = "date";
        }

        if (Objects.equals(type, "pubReview")) {
            System.out.println(type);
            PublicReview pubReview = new PublicReview();
            pubReview.setId(id);
            pubReview.setType(type);
            pubReview.setSpoiler_alert(spoilerAlert);
            pubReview.setGeographicPosition(country);
            pubReview.setRating(rating);
            pubReview.setLanguage(language);
            pubReview.setSource_platform(source);
            Example<Review> examplePubl = Example.of(pubReview);
            //Collection<Review> publicReview = reviewRepository.findAll(examplePubl);
            return reviewRepository.findAll(examplePubl, Sort.by(sorting, "rating"));
            //return publicReview;

        } else if (Objects.equals(type, "premReview")) {
            PremiumReview premReview = new PremiumReview();
            premReview.setId(id);
            premReview.setType(type);
            premReview.setRating(rating);
            premReview.setLanguage(language);
            premReview.setSource_platform(source);
            Example<Review> examplePrem = Example.of(premReview);
            Collection<Review> premiumReview = reviewRepository.findAll(examplePrem,Sort.by(sorting, criterio));
            //Collection<Review> premiumReview = reviewRepository.findAll(examplePrem);
            return premiumReview;

        } else if (spoilerAlert != null || country != null) {
            PublicReview pubReview = new PublicReview();
            pubReview.setType(type);
            pubReview.setGeographicPosition(country);
            pubReview.setSpoiler_alert(spoilerAlert);
            pubReview.setRating(rating);
            pubReview.setLanguage(language);
            pubReview.setSource_platform(source);
            Example<Review> examplePubl = Example.of(pubReview);
            //Collection<Review> publicReviews = reviewRepository.findAll(examplePubl);
            return reviewRepository.findAll(examplePubl, Sort.by(sorting, criterio));
            //return publicReviews;
        } else {

            PremiumReview premReview = new PremiumReview();
            premReview.setRating(rating);
            premReview.setLanguage(language);
            premReview.setSource_platform(source);

            PublicReview pubReview = new PublicReview();
            pubReview.setGeographicPosition(country);
            pubReview.setSpoiler_alert(spoilerAlert);
            pubReview.setRating(rating);
            pubReview.setLanguage(language);
            pubReview.setSource_platform(source);

            Example<Review> examplePubl = Example.of(pubReview);
            Example<Review> examplePrem = Example.of(premReview);
            Collection<Review> premiumReview = reviewRepository.findAll(examplePrem, Sort.by(sorting, criterio));
            Collection<Review> publicReview = reviewRepository.findAll(examplePubl, Sort.by(sorting, criterio));
            //Collection<Review> premiumReviews = reviewRepository.findAll(examplePrem);
            //Collection<Review> publicReviews = reviewRepository.findAll(examplePubl);

            Collection<Review> collectionReviews = Stream.concat(premiumReview.stream(), publicReview.stream()).collect(Collectors.toList());

            return collectionReviews;
        }
/*

        if (criteria == "rating" && orderBy == "asc") {
            return Stream.concat(premiumReview.stream(), publicReview.stream()).sorted(Comparator.comparing(Review::getRating)).collect(Collectors.toList());
            //Collection<Review> collectionOrderBy = collectionReview;
        }
        if (criteria == "rating" && orderBy.toLowerCase() == "dec") {
            return Stream.concat(premiumReview.stream(), publicReview.stream()).sorted(Comparator.comparing(Review::getRating).reversed()).collect(Collectors.toList());
        }
        if (criteria == "date" && orderBy == "asc") {
            return Stream.concat(premiumReview.stream(), publicReview.stream()).sorted(Comparator.comparing(Review::getDate)).collect(Collectors.toList());
        }
        if (criteria == "date" && orderBy == "dec") {
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





