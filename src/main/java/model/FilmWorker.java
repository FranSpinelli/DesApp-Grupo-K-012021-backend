package model;

import java.util.Calendar;

public abstract class FilmWorker {

    private Integer id;
    private String name;
    private String surname;
    private Integer birthYear;
    private Integer deathYear;
    private String type;

    public FilmWorker(Integer anId, String aName, String aSurname, Integer aBirthYear, Integer aDeathYear, String aType){

        if(isAValidStartYear(aBirthYear) && isAValidEndYear(aBirthYear,aDeathYear)){
            this.id = anId;
            this.name = aName;
            this.surname = aSurname;
            this.birthYear = aBirthYear;
            this.deathYear = aDeathYear;
            this.type = aType;
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

    private boolean isAValidStartYear(Integer aBirthYear) {
        Integer currentYear = Calendar.getInstance().get(Calendar.YEAR);

        return aBirthYear > 0 && aBirthYear <= currentYear;
    }

    private boolean isAValidEndYear(Integer aBirthYear, Integer aDeathYear) {

        if(aDeathYear != null){
            return aBirthYear - aDeathYear <= 0;
        }else{
            return true;
        }
    }


}



