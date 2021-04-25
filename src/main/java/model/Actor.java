package model;

public class Actor extends FilmWorker{

    public Actor((Integer anId, String aName, String aSurname, Integer aBirthYear, Integer aDeathYear)
            throws InvalidDatesError{

        super (anId, aName, aSurname, aBirthYear, aDeathYear);
    }
}
