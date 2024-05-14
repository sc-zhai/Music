package haut.zsc.music.repository;

import haut.zsc.music.entity.Singer;
import haut.zsc.music.entity.SongList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface SongListRepository extends JpaRepository<SongList,Integer> {

    default boolean insertSelective(SongList songList){
        int flag=save(songList).getId();
        return flag > 0 ? true : false;
    }
    default boolean saveOrUpdate(SongList songList){
        int flag=save(songList).getId();
        return flag > 0 ? true : false;
    }
    @Modifying
    @Transactional
    @Query(value = "update SongList s set s.title=:title, s.introduction=:introduction,s.style=:style where s.id=:id")
    int saveSongListMsg( String title,String introduction,String style, int id);
    @Modifying
    @Transactional
    @Query(value = "update SongList s set s.pic=:pic where s.id=:id")
    int saveSongListPic(String pic,int id);
    @Modifying
    @Transactional
    int deleteSongListById(Integer id);

    List<SongList> findAllByTitleIsLike(String title);
    List<SongList> findAllByStyleIsLike(String style);
}
