package model;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.PublicReview;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class PublicReviewTest {

    @Test
    void PublicReviewConstructorGettersAndSettersTest(){

        java.util.Date date = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();
        PublicReview aPublicReview = new PublicReview(1,"description", "desc", 2.0, date,
                "platform", "lenguague", false, "nick", "position");

        assertEquals(aPublicReview.getSpoilerAlert(), false);
        assertEquals(aPublicReview.getNickName(), "nick");
        assertEquals(aPublicReview.getGeographicPosition(), "position");

        Date date2 = new GregorianCalendar(2015, Calendar.FEBRUARY, 11).getTime();

        aPublicReview.setGeographicPosition("position2");
        aPublicReview.setSpoilerAlert(true);
        aPublicReview.setNickName("nick2");

        assertEquals(aPublicReview.getSpoilerAlert(), true);
        assertEquals(aPublicReview.getNickName(), "nick2");
        assertEquals(aPublicReview.getGeographicPosition(), "position2");
    }

}