package haut.zsc.music.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    private Integer songId;
    private Integer songListId;
    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    private Byte type;
    private Integer up;

}
