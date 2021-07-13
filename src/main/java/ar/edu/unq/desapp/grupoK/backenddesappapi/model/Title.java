package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "titles")
public class Title {

    @Id
    private Integer id;
    @Column
    private String originalTitle;
    @Column
    private Boolean isAnAdultFilm;
    @Column
    private Integer start_year;
    @Column
    private Integer end_year;
    @Column
    private Integer runtimeMinutes;
    @Column
    private String category;
    @Column
    private String style;
    @Column
    private Double stars;


    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "title_category_id")
    //private TitleCategory category;

    
    @OneToMany(fetch = FetchType.LAZY)
    private List<Review> titleReviews;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<FilmWorker> titleFilmworkers;

    public Integer title_valoracion;

    public Title() {
    }

    public Title(Integer anID, String anOriginalTitle, Boolean anIsAdultIndicator, Integer aStartYear,
                 Integer anEndYear, Integer aRuntimeMinutesAmount, String aTitleCategory, String style,
                 Double stars) throws InvalidDatesError {

        YearVerificator yearVerificator = new YearVerificator();

        if (yearVerificator.isAValidStartYear(aStartYear) &&
                yearVerificator.isAValidEndYearRegardToAStartYear(aStartYear, anEndYear)) {
            this.id = anID;
            this.originalTitle = anOriginalTitle;
            this.isAnAdultFilm = anIsAdultIndicator;
            this.start_year = aStartYear;
            this.end_year = anEndYear;
            this.runtimeMinutes = aRuntimeMinutesAmount;
            this.category = aTitleCategory;
            this.style = style;
            this.stars = stars;
            this.titleReviews = new ArrayList<Review>();
            this.titleFilmworkers = new ArrayList<FilmWorker>();
        } else {
            throw new InvalidDatesError("Wrong dates passed as parameters");
        }
    }

    public Integer getId() {
        return id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public Boolean isAnAdultFilm() {
        return isAnAdultFilm;
    }

    public Integer getStart_year() {
        return start_year;
    }

    public Integer getEndYear() {
        return end_year;
    }

    public Integer getRuntimeMinutes() {
        return runtimeMinutes;
    }

    public List<Review> getReviews() {
        return this.titleReviews;
    }

    public String getTitleCategory() {
        return category;
    }

    public String getTitle_style() {
        return style;
    }

    public Double getTitle_stars() {
        return stars;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public void setIsAnAdultFilmIndicator(Boolean anAdultFilm) {
        isAnAdultFilm = anAdultFilm;
    }

    public void setEndYear(Integer endYear) {
        this.end_year = endYear;
    }

    public void setRuntimeMinutes(Integer runtimeMinutes) {
        this.runtimeMinutes = runtimeMinutes;
    }

    public void setStart_year(Integer start_year) {
        this.start_year = start_year;
    }

    public void setTitleValoracion(Integer valoracion){
        title_valoracion = valoracion;
    }

    public Integer getValoracion(){
        return this.title_valoracion;
    }

    public void setTitleCategory(String titleCategory) {
        this.category = titleCategory;
    }

    public void setTitle_style(String title_style) {
        this.style = title_style;
    }

    public void setTitle_stars(Double stars) {
        this.stars = stars;
    }

    public void addReview(Review aReview) {
        this.titleReviews.add(aReview);
    }

    public void addFilmWorker(FilmWorker filmWorker) {
        this.titleFilmworkers.add(filmWorker);
    }


    public void setStars() {
        Double cantStars = 0.0;
        for (Review review : this.getReviews()) {
            cantStars = +review.getRating();
        }
        this.setTitle_stars(cantStars);
    }

    public void setValoracion(){
        Integer valoracion = 0;
        for (Review review:this.getReviews()){
            valoracion= +review.getLike();
        }
        this.setTitleValoracion(valoracion);
    }
}