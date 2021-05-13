package ar.edu.unq.desapp.grupoK.backenddesappapi.persistence;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.PremiumReview;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.PublicReview;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Review;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Configuration
@Repository
public interface ReviewRepository extends CrudRepository<Review, Integer> {

        Optional<Review> findById(Integer id);

        List<Review> findAll();

        @Query(value = "SELECT DTYPE, ID, DATE, EXTENDED_DESCRIPTION, LANGUAGE, NMBR_DISLIKE, NMBR_LIKE, PLATFORM_WRITERID, RATING, SOURCE_PLATFORM," +
                " SUMMARY_DESCRIPTION, GEOGRAPHIC_POSITION, NICK_NAME, SPOILER_ALERT FROM TITLES_TITLE_REVIEWS JOIN  REVIEWS ON" +
                " TITLES_TITLE_REVIEWS.TITLE_REVIEWS_ID = REVIEWS.ID WHERE TITLE_ID = :titleID AND DTYPE = 'PremiumReview';", nativeQuery = true)
        Collection<PremiumReview> getPremiumReviewsForTitleWithID(@Param("titleID") Integer titleID);

        @Query(value = "SELECT DTYPE, ID, DATE, EXTENDED_DESCRIPTION, LANGUAGE, NMBR_DISLIKE, NMBR_LIKE, PLATFORM_WRITERID, RATING, SOURCE_PLATFORM," +
                " SUMMARY_DESCRIPTION, GEOGRAPHIC_POSITION, NICK_NAME, SPOILER_ALERT FROM TITLES_TITLE_REVIEWS JOIN  REVIEWS ON" +
                " TITLES_TITLE_REVIEWS.TITLE_REVIEWS_ID = REVIEWS.ID WHERE TITLE_ID = :titleID AND DTYPE = 'PublicReview';", nativeQuery = true)
        Collection<PublicReview> getPublicReviewsForTitleWithID(@Param("titleID") Integer titleID);
}
