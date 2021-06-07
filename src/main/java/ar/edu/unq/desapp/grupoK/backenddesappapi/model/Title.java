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
    private Integer startYear;
    @Column
    private Integer endYear;
    @Column
    private Integer runtimeMinutes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "title_category_id")
    private TitleCategory category;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Review> titleReviews;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<FilmWorker> titleFilmworkers;

    public Title() {}

    public Title(Integer anID, String anOriginalTitle, Boolean anIsAdultIndicator, Integer aStartYear,
                 Integer anEndYear, Integer aRuntimeMinutesAmount, TitleCategory aTitleCategory) throws InvalidDatesError {

        YearVerificator yearVerificator = new YearVerificator();

        if(yearVerificator.isAValidStartYear(aStartYear) &&
                yearVerificator.isAValidEndYearRegardToAStartYear(aStartYear, anEndYear)){
            this.id = anID;
            this.originalTitle = anOriginalTitle;
            this.isAnAdultFilm = anIsAdultIndicator;
            this.startYear = aStartYear;
            this.endYear = anEndYear;
            this.runtimeMinutes = aRuntimeMinutesAmount;
            this.titleReviews = new ArrayList<Review>();
            this.category = aTitleCategory;
        }else{
            throw new InvalidDatesError("Wrong dates passed as parameters");
        }
    }

    public Integer getId() { return id; }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public Boolean isAnAdultFilm() { return isAnAdultFilm; }

    public Integer getStartYear() { return startYear; }

    public Integer getEndYear() { return endYear; }

    public Integer getRuntimeMinutes() { return runtimeMinutes; }

    public List<Review> getReviews(){
     return this.titleReviews;
    }

    public String getCategory() {
        return category.getCategoryName();
    }

    public void setId(Integer id){
        this.id = id;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public void setIsAnAdultFilmIndicator(Boolean anAdultFilm) {
        isAnAdultFilm = anAdultFilm;
    }

    public void setEndYear(Integer endYear) {
        this.endYear = endYear;
    }

    public void setRuntimeMinutes(Integer runtimeMinutes) {
        this.runtimeMinutes = runtimeMinutes;
    }

    public void setStartYear(Integer startYear) {
        this.startYear = startYear;
    }

    public void setCategory(String category) {
        this.category = new TitleCategory();
    }

    public void addReview(Review aReview){
        this.titleReviews.add(aReview);
    }
}
