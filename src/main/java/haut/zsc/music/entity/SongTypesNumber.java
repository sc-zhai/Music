package haut.zsc.music.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@IdClass(SongTypesNumberId.class)
public class SongTypesNumber {
    @Id
    private Integer userId;
    @Id
    private int typeId;

    private long numbers=0l;//类型的数量
    public String toString(){
        return "UserID:"+getUserId()+", TypeId:"+getTypeId()+", numbers:"+getNumbers();
    }
}

