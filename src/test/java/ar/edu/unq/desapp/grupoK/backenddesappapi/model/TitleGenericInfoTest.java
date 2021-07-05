package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TitleGenericInfoTest {

    @Test
    void constructorTest(){

        TitleGenericInfo infoGenericaDeUnTitulo = new TitleGenericInfo(1, "Titanic", 100, 3.5, 12);

        assertEquals(1, infoGenericaDeUnTitulo.getId());
        assertEquals("Titanic", infoGenericaDeUnTitulo.getOriginalTitle());
        assertEquals(100, infoGenericaDeUnTitulo.getRuntimeMinutes());
        assertEquals(3.5, infoGenericaDeUnTitulo.getAverageRating());
        assertEquals(12, infoGenericaDeUnTitulo.getNumberOfReviews());
    }
}