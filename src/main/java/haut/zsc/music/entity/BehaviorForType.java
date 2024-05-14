package haut.zsc.music.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
public class BehaviorForType {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ToString.Exclude
  @ManyToOne(targetEntity = User.class,fetch = FetchType.LAZY)
  private User user;
  private Double score;
  @OneToOne
  private Type type;

}
