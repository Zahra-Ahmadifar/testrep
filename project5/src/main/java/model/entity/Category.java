package model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;
/**
 * Created by Asus on 4/11/2021.
 */
@Entity
@Table(name = "t_category")
public class Category extends model.entity.Entity {
    @Column(name = "c_name", columnDefinition = "VARCHAR(255)")
    private String name;

    @OneToMany(mappedBy = "category")
    private Set<CategoryElement> categoryElementList = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<CategoryElement> getCategoryElementList() {
        return categoryElementList;
    }

    public void setCategoryElementList(Set<CategoryElement> categoryElementList) {
        this.categoryElementList = categoryElementList;
    }
}
