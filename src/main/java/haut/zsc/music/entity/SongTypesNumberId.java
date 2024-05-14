package haut.zsc.music.entity;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class SongTypesNumberId implements Serializable {

    private Integer userId;
    private int typeId;
}

