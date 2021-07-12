package ar.edu.unq.desapp.grupoK.backenddesappapi.service;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.FilmWorker;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.PremiumReview;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Review;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Title;
import ar.edu.unq.desapp.grupoK.backenddesappapi.persistence.TitleRepository;
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.serviceLevelExceptions.InexistentTitleWithIDError;
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.serviceLevelExceptions.RepeatedReviewException;
import ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto.EmptyDTOError;
import ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto.FilmWorkerDTO;
import ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto.PremiumReviewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.*;
import javax.persistence.criteria.*;

@Service
public class TitleService {

    @Autowired
    private TitleRepository titleRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public Title save(Title model) {
        return this.titleRepository.save(model);
    }

    public Title findByID(Integer id) throws InexistentTitleWithIDError {
        try {
            return this.titleRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new InexistentTitleWithIDError("There is no Film with id: " + id);
        }
    }

    public Title findTitleById(Integer id) {
        return this.titleRepository.findById(id).get();
    }

    public List<Title> findAll() {
        return this.titleRepository.findAll();
    }


    @Transactional
    public ResponseEntity addNewFilmWorker(FilmWorkerDTO filmWorkerDTO) {

        try {
            filmWorkerDTO.assertEmpty();

            Title titleWithID = findTitleById(filmWorkerDTO.getTitleId());

            //checkForRepeatedPremiumReviewInTitle(titleWithID.getId(), premiumReviewDTO.getPlatformWriterID(), premiumReviewDTO.getSourcePlatform());

            LocalDate currentDate = LocalDate.now();
            FilmWorker aFilmWorker = new FilmWorker(filmWorkerDTO.getName(), filmWorkerDTO.getSurname(),
                    filmWorkerDTO.getBirthYear(), filmWorkerDTO.getDeathYear(), filmWorkerDTO.getCategory());

            titleWithID.addFilmWorker(aFilmWorker);
            Title savedTitle = titleRepository.save(aFilmWorker);
            titleRepository.save(titleWithID);

            return ResponseEntity.ok().body(savedTitle);
        } catch (RepeatedReviewException | InexistentTitleWithIDError | EmptyDTOError e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    public List<Title> findAllByCriteria(LinkedHashMap<String, String> fields) {


        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Title> query = cb.createQuery(Title.class);
        Root<Title> root = query.from(Title.class);
        //Join<Title, Review> reviewJoin = root.join("title_id");

        List<Predicate> predicates = new ArrayList<>();

        fields.keySet().forEach(key ->
            {switch (key) {
                case "id":
                    predicates.add(cb.equal(root.get(key), fields.get(key)));
                    break;
                case "start_year":
                    predicates.add(cb.equal(root.get(key), fields.get(key)));
                    break;
                case "end_year":
                    predicates.add(cb.equal(root.get(key), fields.get(key)));
                   break;
                case "runtimeMinutes":
                    predicates.add(cb.equal(root.get(key), fields.get(key)));
                    break;
                case "category":
                    predicates.add(cb.equal(root.get(key), fields.get(key)));
                    break;
                case "stars":
                    predicates.add(cb.equal(root.get(key), fields.get(key)));
                    break;
                case "style":
                    predicates.add(cb.equal(root.get(key), fields.get(key)));
                    break;
                case "originalTitle":
                    predicates.add(cb.equal(root.get(key), fields.get(key)));
                    break;
                case "anAdultFilm":
                    predicates.add(cb.equal(root.get(key), fields.get(key) ));
                    break;
                //case "rating":
                //    predicates.add(cb.equal(reviewJoin.get()));


            }
        });

        query.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList();


    }
}
