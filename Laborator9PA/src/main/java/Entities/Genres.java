package Entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NamedQuery(name="Genres.findByName",
            query="SELECT g from Genres g WHERE g.name like :name")
public class Genres {
    @Id
    private Long id;
    private String name;

    public Genres() {
    }

    public Genres(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
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
        Genres genres = (Genres) o;
        return Objects.equals(id, genres.id) && Objects.equals(name, genres.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Genres{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
