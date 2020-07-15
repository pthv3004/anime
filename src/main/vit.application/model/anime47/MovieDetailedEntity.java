package model.anime47;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "MovieDetailed", schema = "dbo", catalog = "WibuLover")
public class MovieDetailedEntity implements Serializable {
    @Id
    @Column(name = "movieDetailId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movieDetailId;

    public MovieDetailedEntity() {
    }

    public MovieEntity getMovieEntity() {
        return movieEntity;
    }

    public CategoryEntity getCategoryEntity() {
        return categoryEntity;
    }

    public MovieDetailedEntity(MovieEntity movieEntity, CategoryEntity categoryEntity) {
        this.movieEntity = movieEntity;
        this.categoryEntity = categoryEntity;
        categoryEntity.addMovieEntity(this);
    }

    @JoinColumn(name = "movieId", referencedColumnName = "movieId")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private MovieEntity movieEntity;

    @JoinColumn(name = "cateId", referencedColumnName = "cateId")
    @ManyToOne(cascade = {CascadeType.MERGE})
    private CategoryEntity categoryEntity;


    public int getMovieDetailId() {
        return movieDetailId;
    }

    public void setMovieDetailId(int movieDetailId) {
        this.movieDetailId = movieDetailId;
    }


}
