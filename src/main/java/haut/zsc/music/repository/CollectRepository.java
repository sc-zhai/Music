package haut.zsc.music.repository;

import haut.zsc.music.entity.Collect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CollectRepository extends JpaRepository<Collect,Integer> {
    default boolean insertSelective(Collect collect){
        int flag=save(collect).getId();
        return flag > 0 ? true : false;
    }
    boolean existsByUserIdAndSongId(Integer userId, Integer songId);
    @Modifying
    @Transactional
    int deleteByUserIdAndSongId(Integer userId, Integer songId);
    List<Collect> findCollectsByUserId(Integer userId);
}
