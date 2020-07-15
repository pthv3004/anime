package model.phimmoiz;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Anime", schema = "dbo", catalog = "WibuLover")
public class AnimeEntity {
    private int animeId;
    private String animeName;
    private String image;
    private String animeLink;
    private String status;
    private float iMdb;
    private String genreName;
    private String actorName;
    private String year;

    public AnimeEntity() {
    }
    public AnimeEntity(String name, String image, String link) {
        this.animeName = name;
        this.image = image;
        this.animeLink = link;
    }


    @Id
    @Column(name = "animeId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getAnimeId() {
        return animeId;
    }

    public void setAnimeId(int animeId) {
        this.animeId = animeId;
    }

    @Basic
    @Column(name = "animeName", nullable = true, length = 2147483647)
    public String getAnimeName() {
        return animeName;
    }

    public void setAnimeName(String animeName) {
        this.animeName = animeName;
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
    @Column(name = "animeLink", nullable = true, length = 2147483647)
    public String getAnimeLink() {
        return animeLink;
    }

    public void setAnimeLink(String animeLink) {
        this.animeLink = animeLink;
    }

    @Basic
    @Column(name = "status", nullable = true, length = 2147483647)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "iMDB", nullable = true, precision = 0)
    public float getiMdb() {
        return iMdb;
    }

    public void setiMdb(float iMdb) {
        this.iMdb = iMdb;
    }

    @Basic
    @Column(name = "genreName", nullable = true, length = 50)
    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    @Basic
    @Column(name = "actorName", nullable = true, length = 50)
    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    @Basic
    @Column(name = "year", nullable = true, length = 50)
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimeEntity that = (AnimeEntity) o;
        return animeId == that.animeId &&
                Objects.equals(animeName, that.animeName) &&
                Objects.equals(image, that.image) &&
                Objects.equals(animeLink, that.animeLink) &&
                Objects.equals(status, that.status) &&
                Objects.equals(iMdb, that.iMdb) &&
                Objects.equals(genreName, that.genreName) &&
                Objects.equals(actorName, that.actorName) &&
                Objects.equals(year, that.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(animeId, animeName, image, animeLink, status, iMdb, genreName, actorName, year);
    }
}
