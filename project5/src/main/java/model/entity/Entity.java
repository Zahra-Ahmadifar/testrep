package model.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Asus on 4/14/2021.
 */
@MappedSuperclass
public class Entity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "c_id",columnDefinition = "INTEGER", nullable = false, unique = true)
    public int id;

    @Version
    @Column(name = "version")
    public int version=0;

    @Column(name = "c_active", columnDefinition = "boolean")
    public boolean active;

    @Column(name = "c_disable", columnDefinition = "boolean")
    public boolean disable;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }


}
