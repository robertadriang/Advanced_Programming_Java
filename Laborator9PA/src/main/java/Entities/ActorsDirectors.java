package Entities;

import javax.persistence.*;

@Entity
@Table(name = "ACTORS_DIRECTORS", schema = "STUDENT", catalog = "")
public class ActorsDirectors {
    private Long id;
    private Actors actorsByActorId;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "ACTOR_ID", referencedColumnName = "ID", nullable = false)
    public Actors getActorsByActorId() {
        return actorsByActorId;
    }

    public void setActorsByActorId(Actors actorsByActorId) {
        this.actorsByActorId = actorsByActorId;
    }
}
