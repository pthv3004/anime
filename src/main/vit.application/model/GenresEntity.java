package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Genres", schema = "dbo", catalog = "WibuLover")
public class GenresEntity implements Serializable {
    private int genreId;
    private String genreName;

    public GenresEntity() {
    }

    @Basic
    @Column(name = "genreId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    @Id
    @Column(name = "genreName", nullable = false, length = 50)
    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenresEntity that = (GenresEntity) o;
        return genreId == that.genreId &&
                Objects.equals(genreName, that.genreName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genreId, genreName);
    }
}
