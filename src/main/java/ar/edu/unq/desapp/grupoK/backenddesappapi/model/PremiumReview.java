package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class PremiumReview extends Review {


    public PremiumReview(){};

    public String typeReview;


    public PremiumReview(String aExtendedDescription, String aSumaryDescription, Double aRaiting, LocalDate aDate,
                         String aSourcePlatform, String aPlatformWriterID, String aLanguage, String typeReview) {

        super(aExtendedDescription, aSumaryDescription, aRaiting, aDate, aSourcePlatform, aPlatformWriterID,aSourcePlatform);

        this.typeReview = typeReview;

    }
/*
    private String getTypeReview(){ return this.typeReview}

    public void setTypeReview(String typeReview){ this.typeReview = typeReview}
*/
}
