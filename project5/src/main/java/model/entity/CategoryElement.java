package model.entity;

import javax.persistence.*;
import javax.persistence.Entity;

/**
 * Created by Asus on 4/11/2021.
 */
@Entity
@Table(name = "t_categoryElement")
public class CategoryElement extends model.entity.Entity {
    @Column(name = "c_name", columnDefinition = "VARCHAR(255)")
    private String name;

    @Column(name = "c_code", columnDefinition = "VARCHAR(255)")
    private String code;


    @ManyToOne()
    @JoinColumn(name = "c_categoryId")
    private Category category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
