package model;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TitleTest {

    @Test
    void TitleConstructorAndGettersTestCase2() throws InvalidDatesError {

        TitleCategory aCategory = new TitleCategory(1, "movie");
        Title aMovie = new Title(1,"Titanic", false, 1998, null,100, aCategory);

        assertEquals(aMovie.getId(), 1);
        assertEquals(aMovie.getOriginalTitle(), "Titanic");
        assertFalse(aMovie.isAnAdultFilm());
        assertEquals(aMovie.getStart_year(), 1998);
        assertNull(aMovie.getEndYear());
        assertEquals(aMovie.getRuntimeMinutes(),100);
        assertEquals(aMovie.getCategory(), "movie");
    }

    @Test
    void TitleThrowsAnExceptionWhenInvalidDateIsReceivedCase1(){
        //CASE 1: a title is created with a start year is smaller than 0
        assertThrows(InvalidDatesError.class, () -> {
            TitleCategory aCategory = new TitleCategory(1, "movie");
            new Title(1,"Titanic", false, 0, null,100, aCategory);
        });
    }

    @Test
    void TitleThrowsAnExceptionWhenInvalidDateIsReceivedCase2(){
        //CASE 2: a title is created when an end year is bigger than current year
        assertThrows(InvalidDatesError.class, () -> {
            TitleCategory aCategory = new TitleCategory(1, "movie");
            new Title(1,"Titanic", false, 0, 2022,100, aCategory);
        });
    }

    @Test
    void TitleSettersTest() throws InvalidDatesError {
        TitleCategory aCategory = new TitleCategory(1, "movie");
        Title aMovie = new Title(1,"Titanic", false, 1998, null,100, aCategory);

        assertEquals(aMovie.getId(), 1);
        assertEquals(aMovie.getOriginalTitle(), "Titanic");
        assertFalse(aMovie.isAnAdultFilm());
        assertEquals(aMovie.getStart_year(), 1998);
        assertNull(aMovie.getEndYear());
        assertEquals(aMovie.getRuntimeMinutes(),100);
        assertEquals(aMovie.getCategory(), "movie");

        aMovie.setId(2);
        aMovie.setOriginalTitle("Harry Potter");
        aMovie.setIsAnAdultFilmIndicator(true);
        aMovie.setStart_year(2000);
        aMovie.setEndYear(2010);
        aMovie.setRuntimeMinutes(200);

        assertEquals(aMovie.getId(), 2);
        assertEquals(aMovie.getOriginalTitle(), "Harry Potter");
        assertTrue(aMovie.isAnAdultFilm());
        assertEquals(aMovie.getStart_year(), 2000);
        assertEquals(aMovie.getEndYear(), 2010);
        assertEquals(aMovie.getRuntimeMinutes(),200);
    }

    @Test
    void TitleReviewsTest() throws InvalidDatesError {

        TitleCategory aCategory = new TitleCategory(1, "movie");
        Title aMovie = new Title(1,"Titanic", false, 1998, null,100, aCategory);
        LocalDate date = LocalDate.parse("2020-01-01");
        Review review1 = new PremiumReview("description", "desc", 2.0, date,
                "platform", "AP1","language", "type");
        PublicReview review2 = new PublicReview( "description", "desc", 2.0, false,
                date, "Netflix", "N12", "Frank442", "language", "Argentina", "type");

        aMovie.addReview(review1);
        aMovie.addReview(review2);

        assertEquals(aMovie.getReviews().size(), 2);
    }
}
