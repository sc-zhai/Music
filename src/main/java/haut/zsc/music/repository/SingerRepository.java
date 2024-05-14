package haut.zsc.music.repository;

import haut.zsc.music.entity.Singer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SingerRepository extends JpaRepository<Singer,Integer> {
    default boolean insertSelective(Singer singer){
        int flag=save(singer).getId();
        return flag > 0 ? true : false;
    }
    default boolean saveOrUpdate(Singer singer){
        int flag=save(singer).getId();
        return flag > 0 ? true : false;
    }
    @Modifying
    @Query(value = "update Singer s set s.pic=:pic where s.id=:id")
    int saveSingerPic(String pic,int id);
    @Modifying
    @Transactional
    int deleteSingerById(Integer id);
    List<Singer> findAllByNameIsLike(String name);
    List<Singer> findAllBySex(int sex);
}
