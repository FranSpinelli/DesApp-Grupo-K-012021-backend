package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="reviews")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class PremiumReview {

    @Id
    private Integer id;
    @Column
    private String extendedDescription;
    @Column
    private String summaryDescription;
    @Column
    private Double rating;
    @Column
    private Date date;
    @Column
    private String platform;
    @Column
    private String language;

    public PremiumReview(Integer anID, String aExtendedDescription, String aSumaryDescription, Double aRaiting, Date aDate,
                         String aPlatform, String aLenguage){

        this.id = anID;
        this.extendedDescription = aExtendedDescription;
        this.summaryDescription = aSumaryDescription;
        this.rating = aRaiting;
        this.date = aDate;
        this.platform = aPlatform;
        this.language = aLenguage;
    }

    public Integer getId() {
        return id;
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

    public void setId(Integer id) {
        this.id = id;
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

