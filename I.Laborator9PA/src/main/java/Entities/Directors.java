package Entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Directors {
    private Long id;
    private String name;

    @Id
    @GeneratedValue
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Directors directors = (Directors) o;
        return Objects.equals(id, directors.id) && Objects.equals(name, directors.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
