package ar.edu.unq.desapp.grupoK.backenddesappapi.persistence;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.PremiumReview;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.PublicReview;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Title;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Configuration
@Repository
public interface ReviewRepository extends CrudRepository<PremiumReview, Integer> {

        Optional<PremiumReview> findById(Integer id);

        List<PremiumReview> findAll();

        @Query(value = "SELECT * FROM reviews WHERE dtype = 'PremiumReview'", nativeQuery = true)
        List<PremiumReview> findAllPremiumreviews();

        @Query(value = "SELECT * FROM reviews WHERE dtype = 'PublicReview'", nativeQuery = true)
        List<PublicReview> findAllPublicReviews();
}
