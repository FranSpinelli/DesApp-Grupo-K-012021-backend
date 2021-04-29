package model;

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
}
