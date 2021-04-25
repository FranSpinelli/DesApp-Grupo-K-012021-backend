package model;

public class Director extends FilmWorker{

    public Director(Integer anId, String aName, String aSurname, Integer aBirthYear, Integer aDeathYear)
            throws InvalidDatesError{
        super (anId, aName, aSurname, aBirthYear, aDeathYear);
    }
}
