package haut.zsc.music.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Song implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer singerId;
    private String name;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String introduction;
    private Date createTime;
    private Date updateTime;
    private String pic;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String lyric;
    private String lyricUrl;
    private String url;

    @ManyToMany(cascade = CascadeType.PERSIST,targetEntity = Type.class,fetch = FetchType.EAGER)
    private List<Type> types=new ArrayList<>();
    public void addType(Type type){
        this.types.add(type);
    }

}
