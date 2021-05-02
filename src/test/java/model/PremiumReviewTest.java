package model;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.PremiumReview;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class PremiumReviewTest {

    @Test
    void PremiumReviewConstructorGettersAndSettersTest(){

        java.util.Date date = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();
        PremiumReview aPremiumReview = new PremiumReview(1,"description", "desc", 2.0, date,
                "platform","lenguague");

        assertEquals(aPremiumReview.getId(), 1);
        assertEquals(aPremiumReview.getExtendedDescription(), "description");
        assertEquals(aPremiumReview.getSummaryDescription(), "desc");
        assertEquals(aPremiumReview.getRating(), 2.0,0);
        assertEquals(aPremiumReview.getDate(), date);
        assertEquals(aPremiumReview.getPlatform(), "platform");
        assertEquals(aPremiumReview.getLanguage(), "lenguague");

        Date date2 = new GregorianCalendar(2015, Calendar.FEBRUARY, 11).getTime();

        aPremiumReview.setId(2);
        aPremiumReview.setExtendedDescription("description2");
        aPremiumReview.setSummaryDescription("desc2");
        aPremiumReview.setRating(3.0);
        aPremiumReview.setDate(date2);
        aPremiumReview.setPlatform("platform2");
        aPremiumReview.setLanguage("lenguague2");

        assertEquals(aPremiumReview.getId(), 2);
        assertEquals(aPremiumReview.getExtendedDescription(), "description2");
        assertEquals(aPremiumReview.getSummaryDescription(), "desc2");
        assertEquals(aPremiumReview.getRating(), 3.0,0);
        assertEquals(aPremiumReview.getDate(), date2);
        assertEquals(aPremiumReview.getPlatform(), "platform2");
        assertEquals(aPremiumReview.getLanguage(), "lenguague2");
    }

    @Test
    void PremiumReviewConstructorLikesAndDislikesTest() {

        java.util.Date date = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();
        PremiumReview aPremiumReview = new PremiumReview(1, "description", "desc", 2.0, date,
                "platform", "lenguague");

        aPremiumReview.addLike();
        aPremiumReview.addLike();
        aPremiumReview.addDislike();

        assertEquals(aPremiumReview.getLike(), 2);
        assertEquals(aPremiumReview.getDislike(), 1);

    }

}