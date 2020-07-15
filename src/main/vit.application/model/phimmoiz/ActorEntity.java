package model.phimmoiz;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Actor", schema = "dbo", catalog = "WibuLover")
public class ActorEntity {
    private int actorId;
    private String actorName;
    private String nationality;

    @Basic
    @Column(name = "actorId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getActorId() {
        return actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }

    @Id
    @Column(name = "actorName", nullable = false, length = 50)
    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    @Basic
    @Column(name = "nationality", nullable = false, length = 50)
    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActorEntity that = (ActorEntity) o;
        return actorId == that.actorId &&
                Objects.equals(actorName, that.actorName) &&
                Objects.equals(nationality, that.nationality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actorId, actorName, nationality);
    }
}
