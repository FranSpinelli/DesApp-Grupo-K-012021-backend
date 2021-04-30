package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

public class FilmWorker {

    private  Integer id;
    private String name;
    private String surname;
    private Integer birthYear;
    private Integer deathYear;
    private String type;

    public FilmWorker(Integer anId, String aName, String aSurname, Integer aBirthYear, Integer aDeathYear, String aType) throws InvalidDatesError {

        YearVerificator yearVerificatior = new YearVerificator();

        if(yearVerificatior.isAValidStartYear(aBirthYear) && yearVerificatior.isAValidEndYearRegardToAStartYear(aBirthYear,aDeathYear)){
            this.id = anId;
            this.name = aName;
            this.surname = aSurname;
            this.birthYear = aBirthYear;
            this.deathYear = aDeathYear;
            this.type = aType;
        }else{
            throw new InvalidDatesError("Wrong dates passed as parameters");
        }
    }


    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public String getType() {
        return type;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }

    public void setType(String type) {
        this.type = type;
    }

}



