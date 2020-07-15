package model.anime47;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Movie", schema = "dbo", catalog = "WibuLover")
public class MovieEntity implements Serializable {
    @Id
    @Column(name = "movieId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movieId;
    private String movieName;
    private String movieLink;
    private String status;
    private Integer commentNum;
    private Integer viewNum;
    private String type;
    private String season;
    private String year;
    private String image;
    private float iMdb;
    @Transient
    private String categories;
    @Transient
    private double interactPoint;

    public double getInteractPoint() {
        return interactPoint;
    }

    public void setInteractPoint(double interactPoint) {
        this.interactPoint = interactPoint;
    }

    @PostLoad
    private void convertCategory() {
        categories = movieDetailedEntities.stream().map(c ->
                c.getCategoryEntity().getCategoryName() +
                        (movieDetailedEntities.indexOf(c) == movieDetailedEntities.size() - 1 ? "" : ", "))
                .reduce("", String::concat);
        //calculate interactPoint
        if (year != null) {
            if (this.year.matches("\\d+")) {
                if (this.year.equals("2020") == false) {
                    this.interactPoint = this.viewNum / ((2020 - Integer.parseInt(this.year)) * 360.0);
                }
            } else {
                this.interactPoint = this.viewNum / 360.0;
            }
        }
    }

    public String getCategories() {
        return categories;
    }

    public MovieEntity(String movieName, String movieLink, String status, Integer commentNum, Integer viewNum, String image) {
        this.movieName = movieName;
        this.movieLink = movieLink;
        this.status = status;
        this.commentNum = commentNum;
        this.viewNum = viewNum;
        this.image = image;
    }

    public MovieEntity(String movieName, String movieLink, String image) {
        this.movieName = movieName;
        this.movieLink = movieLink;
        this.image = image;
    }

    public List<MovieDetailedEntity> getMovieDetailedEntities() {
        return movieDetailedEntities;
    }

    public MovieEntity() {
    }


    public MovieEntity(int movieId, String type, String season, String year, float iMdb, String categories, double interactPoint) {
        this.movieId = movieId;
        this.type = type;
        this.season = season;
        this.year = year;
        this.iMdb = iMdb;
        this.categories = categories;
        this.interactPoint = (this.viewNum) / 90.0;
    }


    @Override
    public String toString() {
        return "MovieEntity{" +
                "movieId=" + movieId +
                ", movieName='" + movieName + '\'' +
                ", movieLink='" + movieLink + '\'' +
                ", status='" + status + '\'' +
                ", commentNum=" + commentNum +
                ", viewNum=" + viewNum +
                ", type='" + type + '\'' +
                ", season='" + season + '\'' +
                ", year='" + year + '\'' +
                ", image='" + image + '\'' +
                ", iMdb=" + iMdb +
                ", movieDetailedEntities=" + movieDetailedEntities +
                '}';
    }

    @OneToMany(mappedBy = "movieEntity", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @Setter
    private List<MovieDetailedEntity> movieDetailedEntities;


    public void setMovieDetail(List<MovieDetailedEntity> movieDetailedEntities) {
        this.movieDetailedEntities = movieDetailedEntities;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    @Basic
    @Column(name = "movieName", nullable = true, length = 2147483647)
    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    @Basic
    @Column(name = "movieLink", nullable = true, length = 2147483647)
    public String getMovieLink() {
        return movieLink;
    }

    public void setMovieLink(String movieLink) {
        this.movieLink = movieLink;
    }

    @Basic
    @Column(name = "status", nullable = true, length = 50)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "commentNum", nullable = true)
    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    @Basic
    @Column(name = "viewNum", nullable = true)
    public Integer getViewNum() {
        return viewNum;
    }

    public void setViewNum(Integer viewNum) {
        this.viewNum = viewNum;
    }


    @Basic
    @Column(name = "type", nullable = true, length = 50)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "season", nullable = true, length = 50)
    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    @Basic
    @Column(name = "year", nullable = true, length = 50)
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Basic
    @Column(name = "image", nullable = true, length = 2147483647)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Basic
    @Column(name = "iMDB", nullable = true, precision = 0)
    public float getiMdb() {
        return iMdb;
    }

    public void setiMdb(float iMdb) {
        this.iMdb = iMdb;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieEntity that = (MovieEntity) o;
        return movieId == that.movieId &&
                Objects.equals(movieName, that.movieName) &&
                Objects.equals(movieLink, that.movieLink) &&
                Objects.equals(status, that.status) &&
                Objects.equals(commentNum, that.commentNum) &&
                Objects.equals(viewNum, that.viewNum) &&
                Objects.equals(type, that.type) &&
                Objects.equals(season, that.season) &&
                Objects.equals(year, that.year) &&
                Objects.equals(image, that.image) &&
                Objects.equals(iMdb, that.iMdb);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, movieName, movieLink, status, commentNum, viewNum, type, season, year, image, iMdb);
    }

}
