package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="reviews")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class PremiumReview {

    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String extendedDescription;
    @Column
    private String summaryDescription;
    @Column
    private Integer rating;
    @Column
    private LocalDate date;
    @Column
    private String sourcePlatform;
    @Column
    private String platformWriterID;
    @Column
    private String lenguage;

    public PremiumReview() {}

    public PremiumReview(String aExtendedDescription, String aSumaryDescription, Integer aRaiting, LocalDate aDate,
                         String aSourcePlatform, String aPlatformWriterID, String aLenguage){

        this.extendedDescription = aExtendedDescription;
        this.summaryDescription = aSumaryDescription;
        this.rating = aRaiting;
        this.date = aDate;
        this.sourcePlatform = aSourcePlatform;
        this.platformWriterID = aPlatformWriterID;
        this.lenguage = aLenguage;
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

    public Integer getRating(){
        return rating;
    }

    public LocalDate getDate(){
        return date;
    }

    public String getSourcePlatform(){
        return sourcePlatform;
    }

    public String getPlatformWriterID() {
        return platformWriterID;
    }

    public String getLenguage(){
        return lenguage;
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

    public void setRating(Integer rating){
        this.rating = rating;
    }

    public void setDate(LocalDate date){
        this.date = date;
    }

    public void setSourcePlatform(String sourcePlatform) {
        this.sourcePlatform = sourcePlatform;
    }

    public void setLenguage(String lenguage) {
        this.lenguage = lenguage;
    }

    public void setPlatformWriterID(String platformWriterID) {
        this.platformWriterID = platformWriterID;
    }
}

