/*package ar.edu.unq.desapp.grupoK.backenddesappapi.persistence;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Review;
import org.hibernate.query.Query;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.*;
import java.lang.Double;

@Repository

public class ReviewRepositoryImpl implements ReviewRepositoryCustom{

    private Double rating;

    @PersistenceContext
    private EntityManager entityManager;

    public ReviewRepositoryImpl(Double rating){
        this.rating = rating;
    }

    @Override
    public Collection<Review> findRating(Double rating){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Review> criteriaQuery= criteriaBuilder.createQuery(Review.class);
        Root<Review> root = criteriaQuery.from(Review.class);

        Predicate[] predicates = new Predicate[0];
        predicates[0] = criteriaBuilder.equal(root.get("rating"), rating);

        criteriaQuery.select(root).distinct(true)
                                  .where(predicates);


        List<Review> reviews = entityManager.createQuery(criteriaQuery).getResultList();

        return reviews;

    }


}
*/