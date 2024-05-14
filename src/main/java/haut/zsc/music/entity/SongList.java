package haut.zsc.music.entity;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
public class SongList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String pic;
    private String style;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String introduction;

}
