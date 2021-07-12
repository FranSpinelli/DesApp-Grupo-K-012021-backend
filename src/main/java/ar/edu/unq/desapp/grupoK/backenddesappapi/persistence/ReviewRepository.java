package ar.edu.unq.desapp.grupoK.backenddesappapi.persistence;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.PremiumReview;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.PublicReview;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Review;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Title;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Configuration
@Repository
public interface ReviewRepository extends CrudRepository<Review, Integer>{



        Optional<Review> findById(Integer id);

        List<Review> findAll();




        Collection<Review> findAll(Example<Review> example, Sort by);
        //Collection<Review> findAll(Example<Review> example);

        /*@Query(value = "SELECT r FROM REVIEWS WHERE r.rating =: rating AND r.date =: date AND r.sourcePlatform = sourcePlatform AND r.language = :language " +
                "AND r.country = country AND r.dtype = dtype AND r.spoilerAlert = spoilerAlert;", nativeQuery = true)
        Collection <Review> findAll(@Param("rating") Double rating, @Param("date") Date date, @Param("sourcePlatform") String sourcePlatform,
                                    @Param("language") String language, @Param("country") String country, @Param("dtype") String dtype,
                                    @Param("spoilerAlert") Boolean spoilerAlert);

*/


/*
        SELECT * FROM REVIEWS FROM TITLES_TITLE_REVIEWS JOIN REVIEWS ON
        TITLES_TITLE.REVIEWS_ID = REVIEWS.ID WHERE TITLE_ID = :titleID AND DTYPE = 'PremiumReview' ; nativeQuery = true)
*/
        @Query(value = "SELECT DTYPE, ID, DATE, EXTENDED_DESCRIPTION, LANGUAGE, NMBR_DISLIKE, NMBR_LIKE, PLATFORM_WRITERID, RATING, SOURCE_PLATFORM," +
                " SUMMARY_DESCRIPTION, GEOGRAPHIC_POSITION, NICK_NAME, SPOILER_ALERT, TYPE FROM TITLES_TITLE_REVIEWS JOIN  REVIEWS ON" +
                " TITLES_TITLE_REVIEWS.TITLE_REVIEWS_ID = REVIEWS.ID WHERE TITLE_ID = :titleID AND DTYPE = 'PremiumReview';", nativeQuery = true)
        Collection<PremiumReview> getPremiumReviewsForTitleWithID(@Param("titleID") Integer titleID);

        @Query(value = "SELECT DTYPE, ID, DATE, EXTENDED_DESCRIPTION, LANGUAGE, NMBR_DISLIKE, NMBR_LIKE, PLATFORM_WRITERID, RATING, SOURCE_PLATFORM," +
                " SUMMARY_DESCRIPTION, GEOGRAPHIC_POSITION, NICK_NAME, SPOILER_ALERT FROM TITLES_TITLE_REVIEWS JOIN  REVIEWS ON" +
                " TITLES_TITLE_REVIEWS.TITLE_REVIEWS_ID = REVIEWS.ID WHERE TITLE_ID = :titleID AND DTYPE = 'PublicReview';", nativeQuery = true)
        Collection<PublicReview> getPublicReviewsForTitleWithID(@Param("titleID") Integer titleID);


}
