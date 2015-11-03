package ro.pub.cs.aipi.lab02.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bookstore.language")
public class Language extends PersistentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    protected Long id;
    @Column(name = "name")
    protected String name;
    @Column(name = "code")
    protected String code;
    @Column(name = "description")
    protected String description;

    public Language() {
    }

    public Language(String name, String code, String description) {
        this.name = name;
        this.code = code;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (!this.getClass().equals(object.getClass())) {
            return false;
        }
        Language language = (Language) object;
        if ((this.id.equals(language.getId()))
                && (this.name.equals(language.getName()) && (this.code.equals(language.getCode())))
                && (this.description.equals(language.getDescription()))) {
            return true;
        }
        return false;
    }
}
