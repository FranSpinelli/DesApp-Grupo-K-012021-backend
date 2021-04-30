package model;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.FilmWorker;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.InvalidDatesError;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FilmWorkerTest {

    @Test
    void FilmWorkerConstructorAndGettersTestCASE1() throws InvalidDatesError {
        //CASE 1: no hay error con las fechas
        FilmWorker aFilmWorker = new FilmWorker(1, "Francisco", "Spinelli", 1998, null, "actor");

        assertEquals(aFilmWorker.getId(), 1);
        assertEquals(aFilmWorker.getName(), "Francisco");
        assertEquals(aFilmWorker.getSurname(), "Spinelli");
        assertEquals(aFilmWorker.getBirthYear(), 1998);
        assertNull(aFilmWorker.getDeathYear());
        assertEquals(aFilmWorker.getType(), "actor");
    }

    @Test
    void FilmWorkerThrowsAnExceptionWhenInvalidDateIsReceived(){
        assertThrows(InvalidDatesError.class, () -> {
            new FilmWorker(1, "Francisco", "Spinelli", 1998, 1997, "actor");
        });
    }
}