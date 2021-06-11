package model;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.PremiumReview;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Report;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Review;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ReviewTest {

    @Test
    void PremiumReviewConstructorGettersAndSettersTest(){

        LocalDate date = LocalDate.of(2020, 1, 1);
        Review aPremiumReview = new PremiumReview("description", "desc", 2.0, date,
                "platform", "P1","lenguague", "type");

        assertNull(aPremiumReview.getId());
        assertEquals(aPremiumReview.getExtendedDescription(), "description");
        assertEquals(aPremiumReview.getSummaryDescription(), "desc");
        assertEquals(aPremiumReview.getRating(), 2.0,0);
        assertEquals(aPremiumReview.getDate(), date);
        assertEquals(aPremiumReview.getSourcePlatform(), "platform");
        assertEquals(aPremiumReview.getPlatformWriterID(), "P1");
        assertEquals(aPremiumReview.getLanguage(), "lenguague");
        assertEquals(aPremiumReview.getReports().size(), 0);

        LocalDate date2 = LocalDate.of(2020, 1, 2);

        aPremiumReview.setId(2);
        aPremiumReview.setExtendedDescription("description2");
        aPremiumReview.setSummaryDescription("desc2");
        aPremiumReview.setRating(3.0);
        aPremiumReview.setDate(date2);
        aPremiumReview.setSourcePlatform("platform2");
        aPremiumReview.setPlatformWriterID("P2");
        aPremiumReview.setLanguage("lenguague2");

        assertEquals(aPremiumReview.getId(), 2);
        assertEquals(aPremiumReview.getExtendedDescription(), "description2");
        assertEquals(aPremiumReview.getSummaryDescription(), "desc2");
        assertEquals(aPremiumReview.getRating(), 3.0,0);
        assertEquals(aPremiumReview.getDate(), date2);
        assertEquals(aPremiumReview.getSourcePlatform(), "platform2");
        assertEquals(aPremiumReview.getPlatformWriterID(), "P2");
        assertEquals(aPremiumReview.getLanguage(), "lenguague2");
    }

    @Test
    void PremiumReviewGetLikeAndGetDislikeTest(){
        LocalDate date = LocalDate.of(2020, 1, 1);
        Review aPremiumReview = new PremiumReview("description", "desc", 2.0, date,
                "platform", "P1","lenguague", "type");

        assertEquals(aPremiumReview.getLike(), 0);
        assertEquals(aPremiumReview.getDislike(), 0);
    }

    @Test
    void PremiumReviewAddLikeAndAddDislikeTest(){
        LocalDate date = LocalDate.of(2020, 1, 1);
        Review aPremiumReview = new PremiumReview("description", "desc", 2.0, date,
                "platform", "P1","lenguague", "type");

        aPremiumReview.addLike();
        aPremiumReview.addLike();
        aPremiumReview.addLike();

        aPremiumReview.addDislike();
        aPremiumReview.addDislike();

        assertEquals(aPremiumReview.getLike(), 3);
        assertEquals(aPremiumReview.getDislike(), 2);
    }

    @Test
    void ReviewAddReportTest(){
        LocalDate date = LocalDate.of(2020, 1, 1);
        Review aPremiumReview = new PremiumReview("description", "desc", 2.0, date,
                "platform", "P1","lenguague", "type");

        assertEquals(aPremiumReview.getReports().size(), 0);

        Report aReport = new Report("aCause", "netflix", "n12", "fran");
        aPremiumReview.addReport(aReport);

        assertEquals(aPremiumReview.getReports().size(), 1);
    }
}