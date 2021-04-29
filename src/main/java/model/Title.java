package model;

import java.util.Calendar;

public class Title {

    private Integer id;
    private String originalTitle;
    private Boolean isAnAdultFilm;
    private Integer startYear;
    private Integer endYear;
    private Integer runtimeMinutes;
    private String type;

    public Title(Integer anID, String anOriginalTitle, Boolean anIsAdultIndicator, Integer aStartYear,
                 Integer anEndYear, Integer aRuntimeMinutesAmount, String aType) throws InvalidDatesError {

        YearVerificator yearVerificator = new YearVerificator();

        if(yearVerificator.isAValidStartYear(aStartYear) &&
                yearVerificator.isAValidEndYearRegardToAStartYear(aStartYear, anEndYear)){
            this.id = anID;
            this.originalTitle = anOriginalTitle;
            this.isAnAdultFilm = anIsAdultIndicator;
            this.startYear = aStartYear;
            this.endYear = anEndYear;
            this.runtimeMinutes = aRuntimeMinutesAmount;
            this.type = aType;

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

    public String getType() {
        return type;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public void setAnAdultFilm(Boolean anAdultFilm) {
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

    public void setType(String type) {
        this.type = type;
    }
}
