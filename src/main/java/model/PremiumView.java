package model;

import java.util.Date;

public class PremiumView {

        String extendedDescription;
        String summaryDescription;
        Float rating;
        Date date;
        String platform;
        String language;


    public PremiumView(String aExtendedDescription, String aSumaryDescription, Float aRaiting, Date aDate,
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

    public Float getRating(){
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

    public void setRating(Float rating){
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

