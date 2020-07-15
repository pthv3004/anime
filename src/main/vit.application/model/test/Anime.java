package model.test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Anime {
    private String name;
    private String image;
    private String link;
    private String status;
    private int commentNum;
    private int viewNum;
    private String category;
    private String type;
    private String season;
    private String year;

    public Anime(String name, String image, String link, String status, int commentNum, int viewNum) {
        this.name = name;
        this.image = image;
        this.link = link;
        this.status = status;
        this.commentNum = commentNum;
        this.viewNum = viewNum;
    }

    public Anime(String name, String image, String link, int viewNum, String year) {
        this.name = name;
        this.image = image;
        this.link = link;
        this.viewNum = viewNum;
        this.year = year;
    }

    public Anime(String name, String image, String link) {
        this.name = name;
        this.image = image;
        this.link = link;
    }

    @Override
    public String toString() {
        return "Anime{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", link='" + link + '\'' +
                ", status='" + status + '\'' +
                ", commentNum=" + commentNum +
                ", viewNum=" + viewNum +
                ", category='" + category + '\'' +
                ", type='" + type + '\'' +
                ", season='" + season + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}