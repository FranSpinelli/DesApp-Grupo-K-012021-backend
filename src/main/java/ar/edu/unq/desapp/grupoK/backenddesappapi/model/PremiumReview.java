package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class PremiumReview extends Review {

    public PremiumReview(){};

    public PremiumReview(String aExtendedDescription, String aSumaryDescription, Integer aRaiting, LocalDate aDate,
                         String aSourcePlatform, String aPlatformWriterID, String aLanguage){

        super(aExtendedDescription, aSumaryDescription, aRaiting, aDate, aSourcePlatform, aPlatformWriterID, aLanguage);
    }
}
