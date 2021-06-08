package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Category {

    @Id
    private Integer id;
    @Column
    private String categoryName;

    public Category(){}

    public Category(Integer anID, String aCategory){

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
