package model;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.FilmWorker;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.FilmWorkerCategory;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.InvalidDatesError;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FilmWorkerTest {

    @Test
    void FilmWorkerConstructorAndGettersTestCASE1() throws InvalidDatesError {
        //CASE 1: no hay error con las fechas
        FilmWorkerCategory aCategory = new FilmWorkerCategory(1, "Director");
        FilmWorker aFilmWorker = new FilmWorker(1, "Francisco", "Spinelli", 1998, null, aCategory);

        assertEquals(aFilmWorker.getId(), 1);
        assertEquals(aFilmWorker.getName(), "Francisco");
        assertEquals(aFilmWorker.getSurname(), "Spinelli");
        assertEquals(aFilmWorker.getBirthYear(), 1998);
        assertNull(aFilmWorker.getDeathYear());
        assertEquals(aFilmWorker.getCategory(), "Director");
    }

    @Test
    void FilmWorkerThrowsAnExceptionWhenInvalidDateIsReceived(){
        assertThrows(InvalidDatesError.class, () -> {
            FilmWorkerCategory aCategory = new FilmWorkerCategory(1, "Director");
            new FilmWorker(1, "Francisco", "Spinelli", 1998, 1997, aCategory);
        });
    }

    @Test
    void FilmWorkerSettersTest() throws InvalidDatesError {
        FilmWorkerCategory aCategory = new FilmWorkerCategory(1, "Director");
        FilmWorker aFilmWorker = new FilmWorker(1, "Francisco", "Spinelli", 1998, null, aCategory);

        assertEquals(aFilmWorker.getId(), 1);
        assertEquals(aFilmWorker.getName(), "Francisco");
        assertEquals(aFilmWorker.getSurname(), "Spinelli");
        assertEquals(aFilmWorker.getBirthYear(), 1998);
        assertNull(aFilmWorker.getDeathYear());
        assertEquals(aFilmWorker.getCategory(), "Director");

        aFilmWorker.setId(2);
        aFilmWorker.setName("Emiliano");
        aFilmWorker.setSurname("Perez");
        aFilmWorker.setBirthYear(1999);
        aFilmWorker.setDeathYear(2010);

        assertEquals(aFilmWorker.getId(), 2);
        assertEquals(aFilmWorker.getName(), "Emiliano");
        assertEquals(aFilmWorker.getSurname(), "Perez");
        assertEquals(aFilmWorker.getBirthYear(), 1999);
        assertEquals(aFilmWorker.getDeathYear(), 2010);
    }
}