package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "filmworker_categories")
public class FilmWorkerCategory extends Category{

    public  FilmWorkerCategory(){}

    public FilmWorkerCategory(Integer anID, String aFilmWorkerCategory){
        super(anID, aFilmWorkerCategory);
    }
}
