package model;

public class Movie extends Title{

    public Movie(Integer anID, String anOriginalTitle, Boolean anIsAdultIndicator, Integer aStartYear,
                 Integer anEndYear, Integer aRuntimeMinutesAmount) throws InvalidDatesError {

        super(anID, anOriginalTitle, anIsAdultIndicator, aStartYear, anEndYear, aRuntimeMinutesAmount);
    }
//por qué en el constructor está endYear?????

}