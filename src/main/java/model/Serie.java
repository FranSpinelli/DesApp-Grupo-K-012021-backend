package model;

public class Serie extends Title{

    public Serie(Integer anID, String anOriginalTitle, Boolean anIsAdultIndicator, Integer aStartYear,
                 Integer anEndYear, Integer aRuntimeMinutesAmount) throws InvalidDatesError {

        super(anID, anOriginalTitle, anIsAdultIndicator, aStartYear, anEndYear, aRuntimeMinutesAmount);
    }



}
