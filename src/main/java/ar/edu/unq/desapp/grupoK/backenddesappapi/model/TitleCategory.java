package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="title_categories")
public class TitleCategory {

    @Id
    private Integer id;
    @Column
    private String categoryName;

    public TitleCategory(){}

    public TitleCategory(Integer anID, String aCategory){

        this.id = anID;
        this.categoryName = aCategory;
    }

    public Integer getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
