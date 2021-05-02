package model;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.PremiumReview;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class PremiumReviewTest {

    @Test
    void PremiumReviewConstructorGettersAndSettersTest(){

        LocalDate date = LocalDate.of(2020, 1, 1);
        PremiumReview aPremiumReview = new PremiumReview("description", "desc", 2, date,
                "platform", "P1","lenguague");

        assertNull(aPremiumReview.getId());
        assertEquals(aPremiumReview.getExtendedDescription(), "description");
        assertEquals(aPremiumReview.getSummaryDescription(), "desc");
        assertEquals(aPremiumReview.getRating(), 2.0,0);
        assertEquals(aPremiumReview.getDate(), date);
        assertEquals(aPremiumReview.getSourcePlatform(), "platform");
        assertEquals(aPremiumReview.getLenguage(), "lenguague");

        LocalDate date2 = LocalDate.of(2020, 1, 2);

        aPremiumReview.setId(2);
        aPremiumReview.setExtendedDescription("description2");
        aPremiumReview.setSummaryDescription("desc2");
        aPremiumReview.setRating(3);
        aPremiumReview.setDate(date2);
        aPremiumReview.setSourcePlatform("platform2");
        aPremiumReview.setLenguage("lenguague2");

        assertEquals(aPremiumReview.getId(), 2);
        assertEquals(aPremiumReview.getExtendedDescription(), "description2");
        assertEquals(aPremiumReview.getSummaryDescription(), "desc2");
        assertEquals(aPremiumReview.getRating(), 3.0,0);
        assertEquals(aPremiumReview.getDate(), date2);
        assertEquals(aPremiumReview.getSourcePlatform(), "platform2");
        assertEquals(aPremiumReview.getLenguage(), "lenguague2");
    }
}