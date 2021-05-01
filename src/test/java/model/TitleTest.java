package model;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.InvalidDatesError;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Title;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TitleTest {

    @Test
    void TitleConstructorAndGettersTestCase2() throws InvalidDatesError {

        Title aMovie = new Title(1,"Titanic", false, 1998, null,100, "movie");

        assertEquals(aMovie.getId(), 1);
        assertEquals(aMovie.getOriginalTitle(), "Titanic");
        assertFalse(aMovie.isAnAdultFilm());
        assertEquals(aMovie.getStartYear(), 1998);
        assertNull(aMovie.getEndYear());
        assertEquals(aMovie.getRuntimeMinutes(),100);
        assertEquals(aMovie.getType(), "movie");
    }

    @Test
    void TitleThrowsAnExceptionWhenInvalidDateIsReceivedCase1(){
        //CASE 1: a title is created with a start year is smaller than 0
        assertThrows(InvalidDatesError.class, () -> {
        new Title(1,"Titanic", false, 0, null,100, "movie");
        });
    }

    @Test
    void TitleThrowsAnExceptionWhenInvalidDateIsReceivedCase2(){
        //CASE 2: a title is created when an end year is bigger than current year
        assertThrows(InvalidDatesError.class, () -> {
            new Title(1,"Titanic", false, 0, 2022,100, "movie");
        });
    }

    @Test
    void TitleSettersTest() throws InvalidDatesError {
        Title aMovie = new Title(1,"Titanic", false, 1998, null,100, "movie");

        assertEquals(aMovie.getId(), 1);
        assertEquals(aMovie.getOriginalTitle(), "Titanic");
        assertFalse(aMovie.isAnAdultFilm());
        assertEquals(aMovie.getStartYear(), 1998);
        assertNull(aMovie.getEndYear());
        assertEquals(aMovie.getRuntimeMinutes(),100);
        assertEquals(aMovie.getType(), "movie");

        aMovie.setId(2);
        aMovie.setOriginalTitle("Harry Potter");
        aMovie.setIsAnAdultFilmIndicator(true);
        aMovie.setStartYear(2000);
        aMovie.setEndYear(2010);
        aMovie.setRuntimeMinutes(200);
        aMovie.setType("serie");

        assertEquals(aMovie.getId(), 2);
        assertEquals(aMovie.getOriginalTitle(), "Harry Potter");
        assertTrue(aMovie.isAnAdultFilm());
        assertEquals(aMovie.getStartYear(), 2000);
        assertEquals(aMovie.getEndYear(), 2010);
        assertEquals(aMovie.getRuntimeMinutes(),200);
        assertEquals(aMovie.getType(), "serie");
    }
}
