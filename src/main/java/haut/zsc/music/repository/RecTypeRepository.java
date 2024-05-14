package haut.zsc.music.repository;

import haut.zsc.music.entity.BehaviorForType;

import haut.zsc.music.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecTypeRepository extends JpaRepository<BehaviorForType,Integer> {
   List<BehaviorForType> findByUserOrderByScoreDesc(User user);
   List<BehaviorForType> findBehaviorForTypesByUser_IdOrderByScoreDesc(Integer id);
}
