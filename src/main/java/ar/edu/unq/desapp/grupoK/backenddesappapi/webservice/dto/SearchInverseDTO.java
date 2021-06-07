package ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto;

import java.io.StringWriter;

public class SearchInverseDTO {

    private Integer id;
    private Double rating;
    private String title;
    private Boolean isAdult;
    private Integer startYear;
    private Integer endYear;
    private Integer runTimeMinutes;
    private String genre;
    private String director;
    private String writer;
    private String actor;

    public SearchInverseDTO(){};

    public SearchInverseDTO(Integer id, Double rating,String title, Boolean isAdult, Integer startYear, Integer endYear,
                            Integer runTimeMinutes, String genre, String director, String writer, String actor){
        this.id = id;
        this.rating = rating;
        this.title = title;
        this.isAdult = isAdult;
        this.startYear = startYear;
        this.endYear = endYear;
        this.runTimeMinutes = runTimeMinutes;
        this.genre = genre;
        this.director = director;
        this.writer = writer;
        this.actor = actor;
    }

    public Integer getId() {
        return id;
    }

    public Double getRating() {
        return rating;
    }

    public String getTitle() {
        return title;
    }

    public Boolean getAdult() {
        return isAdult;
    }

    public Integer getStartYear() {
        return startYear;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public Integer getRunTimeMinutes() {
        return runTimeMinutes;
    }

    public String getGenre() {
        return genre;
    }

    public String getDirector() {
        return director;
    }

    public String getWriter() {
        return writer;
    }

    public String getActor() {
        return actor;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAdult(Boolean adult) {
        isAdult = adult;
    }

    public void setStartYear(Integer startYear) {
        this.startYear = startYear;
    }

    public void setEndYear(Integer endYear) {
        this.endYear = endYear;
    }

    public void setRunTimeMinutes(Integer runTimeMinutes) {
        this.runTimeMinutes = runTimeMinutes;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }
}
