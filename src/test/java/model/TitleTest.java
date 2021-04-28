package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TitleTest {

    @Test
    void TitleConstructorAndGettersTestCase2() throws InvalidDatesError {

        Title aMovie = new Movie(1,"Titanic", false, 1998, null,100);

        assertEquals(aMovie.getId(), 1);
        assertEquals(aMovie.getOriginalTitle(), "Titanic");
        assertFalse(aMovie.isAnAdultFilm());
        assertEquals(aMovie.getStartYear(), 1998);
        assertNull(aMovie.getEndYear());
        assertEquals(aMovie.getRuntimeMinutes(),100);
    }

    @Test
    void TitleThrowsAnExceptionWhenInvalidDateIsReceivedCase1(){
        //CASE 1: a movie is created with a start year is smaller than 0
        assertThrows(InvalidDatesError.class, () -> {
        new Movie(1,"Titanic", false, 0, null,100);
        });
    }
}