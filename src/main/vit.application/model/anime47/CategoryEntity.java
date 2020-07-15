package model.anime47;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Category", schema = "dbo", catalog = "WibuLover")
public class CategoryEntity {
    @Id
    @Column(name = "cateId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cateId;
    private String categoryName;
    @Column(name = "viewNum", nullable = true)
    private int viewNum;

    public int getViewNum() {
        return viewNum;
    }

    public void setViewNum(int viewNum) {
        this.viewNum += viewNum;
    }

    public CategoryEntity() {
    }
    public CategoryEntity( String categoryName){
        this.movieDetailedEntities = new ArrayList<>();
        this.categoryName = categoryName;
    }




    public void addMovieEntity(MovieDetailedEntity movieDetailedEntity){

        movieDetailedEntities.add(movieDetailedEntity);
    }

    public int getCateId() {
        return cateId;
    }

    public void setCateId(int cateId) {
        this.cateId = cateId;
    }

    @OneToMany(mappedBy = "categoryEntity",cascade = {CascadeType.MERGE})
    private Collection<MovieDetailedEntity> movieDetailedEntities;


    public void setMovieDetailedEntities(Collection<MovieDetailedEntity> movieDetailedEntities) {
        this.movieDetailedEntities = movieDetailedEntities;
    }

    @Basic
    @Column(name = "categoryName", nullable = true, length = 2147483647)
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "CategoryEntity{" +
                "cateId=" + cateId +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryEntity that = (CategoryEntity) o;
        return cateId == that.cateId &&
                Objects.equals(categoryName, that.categoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cateId, categoryName);
    }
}
