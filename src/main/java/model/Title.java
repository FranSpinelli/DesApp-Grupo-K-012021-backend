package model;

import java.util.Calendar;

public abstract class Title {

    private Integer id;
    private String originalTitle;
    private Boolean isanAdultFilm;
    private Integer startYear;
    private Integer endYear;
    private Integer runtimeMinutes;

    public Title(Integer anID, String anOriginalTitle, Boolean anIsAdultIndicator, Integer aStartYear,
                 Integer anEndYear, Integer aRuntimeMinutesAmount) throws InvalidDatesError {
        if(isAValidStartYear(aStartYear) && isAValidEndYear(aStartYear, anEndYear)){
            this.id = anID;
            this.originalTitle = anOriginalTitle;
            this.isanAdultFilm = anIsAdultIndicator;
            this.startYear = aStartYear;
            this.endYear = anEndYear;
            this.runtimeMinutes = aRuntimeMinutesAmount;
        }else{
            throw new InvalidDatesError("Wrong dates passed as parameters");
        }
    }

    public Integer getId() { return id; }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public Boolean isAnAdultFilm() { return isanAdultFilm; }

    public Integer getStartYear() { return startYear; }

    public Integer getEndYear() { return endYear; }

    public Integer getRuntimeMinutes() { return runtimeMinutes; }

    //PRIVATE METHODS
    private boolean isAValidStartYear(Integer aStartYear) {
        Integer currentYear = Calendar.getInstance().get(Calendar.YEAR);

        return aStartYear > 0 && aStartYear <= currentYear;
    }

    private boolean isAValidEndYear(Integer aStartYear, Integer anEndYear) {

        if(anEndYear != null){
            return aStartYear - anEndYear <= 0;
        }else{
            return true;
        }
    }
}
