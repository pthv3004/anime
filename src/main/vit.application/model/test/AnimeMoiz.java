package model.test;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AnimeMoiz {
    private String name;
    private String image;
    private String link;
    private String status;
    private float iMDb;
    private String category;
    private String year;

    public AnimeMoiz(String name, String image, String link) {
        this.name = name;
        this.image = image;
        this.link = link;
    }

}
