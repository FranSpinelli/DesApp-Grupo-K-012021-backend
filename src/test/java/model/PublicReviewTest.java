package model;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.PublicReview;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class PublicReviewTest {

    @Test
    void PublicReviewConstructorGettersAndSettersTest(){

        LocalDate date = LocalDate.of(2020, 1, 1);
        PublicReview aPublicReview = new PublicReview( "description", "desc", 2.0, false,
                date, "Netflix", "N12", "nick", "language", "position", "type");

        assertEquals(aPublicReview.getSpoiler_alert(), false);
        assertEquals(aPublicReview.getNickName(), "nick");
        assertEquals(aPublicReview.getGeographicPosition(), "position");

        Date date2 = new GregorianCalendar(2015, Calendar.FEBRUARY, 11).getTime();

        aPublicReview.setGeographicPosition("position2");
        aPublicReview.setSpoiler_alert(true);
        aPublicReview.setNickName("nick2");

        assertEquals(aPublicReview.getSpoiler_alert(), true);
        assertEquals(aPublicReview.getNickName(), "nick2");
        assertEquals(aPublicReview.getGeographicPosition(), "position2");
    }

}