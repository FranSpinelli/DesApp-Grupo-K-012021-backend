package ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.InvalidDatesError;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.YearVerificator;

import javax.persistence.Column;
import javax.persistence.Id;

public class FilmWorkerDTO {

    private Integer titleID;
    private String name;
    private String surname;
    private Integer birthYear;
    private Integer deathYear;
    private String filmworkerCategory;



    public FilmWorkerDTO() {}

    public FilmWorkerDTO(Integer titleID, String aName, String aSurname, Integer aBirthYear, Integer aDeathYear, String aFilmworkerCategory) throws InvalidDatesError {


        this.titleID = titleID;
        this.name = aName;
        this.surname = aSurname;
        this.birthYear = aBirthYear;
        this.deathYear = aDeathYear;
        this.filmworkerCategory = aFilmworkerCategory;

    }


    public Integer getTitleId() {
        return titleID;
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

    public String getCategory() {
        return filmworkerCategory;
    }

    public void assertEmpty() throws EmptyDTOError {

        if (titleID == null || name == null || surname == null || birthYear == null || deathYear == null ||
                filmworkerCategory == null) {
            throw new EmptyDTOError("Wrong json received as parameter");
        }

        if (this.name.isEmpty() || this.surname.isEmpty() || this.filmworkerCategory.isEmpty()) {
            throw new EmptyDTOError("There is an empty field in the body json");
        }

    }
}

