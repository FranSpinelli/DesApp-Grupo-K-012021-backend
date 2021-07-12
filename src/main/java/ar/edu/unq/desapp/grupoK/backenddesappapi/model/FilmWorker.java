package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import javax.persistence.*;

@Entity
@Table(name = "filmworkers")
public class FilmWorker {

    @Id
    private Integer id;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private Integer birthYear;
    @Column
    private Integer deathYear;
    @Column
    private String filmworkerCategory;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "filmworker_category_id")
    //private FilmWorkerCategory category;

    public FilmWorker() {}

    public FilmWorker(Integer anId, String aName, String aSurname, Integer aBirthYear, Integer aDeathYear, String aFilmworkerCategory) throws InvalidDatesError {

        YearVerificator yearVerificatior = new YearVerificator();

        if(yearVerificatior.isAValidStartYear(aBirthYear) && yearVerificatior.isAValidEndYearRegardToAStartYear(aBirthYear,aDeathYear)){
            this.id = anId;
            this.name = aName;
            this.surname = aSurname;
            this.birthYear = aBirthYear;
            this.deathYear = aDeathYear;
            this.filmworkerCategory = aFilmworkerCategory;
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

    public String getCategory() {
        return filmworkerCategory;
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

    public void setFilmworkerCategory(String category) { this.filmworkerCategory = category;}

}



