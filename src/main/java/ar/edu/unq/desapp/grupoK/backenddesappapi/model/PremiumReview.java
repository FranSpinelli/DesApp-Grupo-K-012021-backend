package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import java.util.Date;

public class PremiumReview {

        private String extendedDescription;
        private String summaryDescription;
        private Double rating;
        private Date date;
        private String platform;
        private String language;


    public PremiumReview(String aExtendedDescription, String aSumaryDescription, Double aRaiting, Date aDate,
                         String aPlatform, String aLenguage){

        this.extendedDescription = aExtendedDescription;
        this.summaryDescription = aSumaryDescription;
        this.rating = aRaiting;
        this.date = aDate;
        this.platform = aPlatform;
        this.language = aLenguage;
    }


    public String getExtendedDescription(){
        return extendedDescription;
    }

    public String getSummaryDescription(){
        return summaryDescription;
    }

    public Double getRating(){
        return rating;
    }

    public Date getDate(){
        return date;
    }

    public String getPlatform(){
        return platform;
    }

    public String getLanguage(){
        return language;
    }

    public void setExtendedDescription(String extendedDescription) {
        this.extendedDescription = extendedDescription;
    }

    public void setSummaryDescription(String summaryDescription){
        this.summaryDescription = summaryDescription;
    }

    public void setRating(Double rating){
        this.rating = rating;
    }

    public  void setDate(Date date){
        this.date = date;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}

