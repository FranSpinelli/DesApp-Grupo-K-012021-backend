package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "title_categories")
public class TitleCategory extends Category{

    public TitleCategory(){}

    public TitleCategory(Integer anID, String aCategory){
        super(anID, aCategory);
    }
}
