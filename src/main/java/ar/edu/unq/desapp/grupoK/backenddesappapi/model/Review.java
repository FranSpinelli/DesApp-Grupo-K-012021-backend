package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="reviews")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
abstract public class Review {

    @TableGenerator(name = "Review_ID_Generator", initialValue = 20)
    @Id
    @GeneratedValue(generator = "Review_ID_Generator")
    private Integer id;
    @Column
    private String extendedDescription;
    @Column
    private String summaryDescription;
    @Column
    private Double rating;
    @Column
    private LocalDate date;
    @Column
    private String source_platform;
    @Column
    private String platformWriterID;
    @Column
    private String language;
    @Column
    private Integer nmbrLike;
    @Column
    private Integer nmbrDislike;
    @Column
    private Integer title_id;



    @OneToMany(fetch = FetchType.LAZY)
    private List<Report> reports;

    public  Review() {}
    public String type;


    public Review(String aExtendedDescription, String aSumaryDescription, Double aRaiting, LocalDate aDate,
                  String aSourcePlatform, String aPlatformWriterID, String aLanguage, Integer title_id){

        this.extendedDescription = aExtendedDescription;
        this.summaryDescription = aSumaryDescription;
        this.rating = aRaiting;
        this.date = aDate;
        this.source_platform = aSourcePlatform;
        this.platformWriterID = aPlatformWriterID;
        this.language = aLanguage;
        this.title_id = title_id;
        this.nmbrLike = 0;
        this.nmbrDislike = 0;




        reports = new ArrayList<Report>();

    }

    public Integer gettTitle_id() { return title_id;  }

    public void setTitle_id(Integer id_title) { this.title_id = id_title;     }

    public Integer getId() { return id; }

    public String getExtendedDescription(){
        return extendedDescription;
    }

    public String getSummaryDescription(){
        return summaryDescription;
    }

    public Double getRating(){
        return rating;
    }

    public LocalDate getDate(){
        return date;
    }

    public String getSource_platform(){
        return source_platform;
    }

    public String getPlatformWriterID() {
        return platformWriterID;
    }

    public String getLanguage(){
        return language;
    }

    public Integer getLike() {
        return nmbrLike;
    }

    public Integer getDislike() {
        return nmbrDislike;
    }

    public void setId(Integer id) { this.id = id; }

    public void setExtendedDescription(String extendedDescription) {
        this.extendedDescription = extendedDescription;
    }

    public void setSummaryDescription(String summaryDescription){
        this.summaryDescription = summaryDescription;
    }

    public void setRating(Double rating){
        this.rating = rating;
    }

    public void setDate(LocalDate date){
        this.date = date;
    }

    public void setSource_platform(String source_platform) {
        this.source_platform = source_platform;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setPlatformWriterID(String platformWriterID) {
        this.platformWriterID = platformWriterID;
    }

    public void addLike(){
        this.nmbrLike++;
    }

    public void addDislike(){
        this.nmbrDislike++;
    }

    public void addReport(Report aReport) {
        this.reports.add(aReport);
    }

    public Collection<Report> getReports() {
        return reports;
    }

    public void setType(String type){ this.type = type;}


}

