package model;

public class Writter extends FilmWorker{

    public Writter(Integer anId, String aName, String aSurname, Integer aBirthYear, Integer aDeathYear)
            throws InvalidDatesError{
        super (anId, aName, aSurname, aBirthYear, aDeathYear);
    }


}
