package haut.zsc.music.repository;

import haut.zsc.music.entity.Song;
import haut.zsc.music.entity.SongList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SongRepository extends JpaRepository<Song,Integer> {
    default boolean insertSelective(Song song){
        int flag=save(song).getId();
        return flag > 0 ? true : false;
    }
    default boolean saveOrUpdate(Song song){
        int flag=save(song).getId();
        return flag > 0 ? true : false;
    }

    @Modifying
    @Transactional
    @Query(value = "update Song s set s.name=:name, s.introduction=:introduction,s.lyric=:lyric where s.id=:id")
    int saveSongMsg( String name,String introduction,String lyric, int id);
    @Modifying
    @Transactional
    @Query(value = "update Song s set s.pic=:pic where s.id=:id")
    int saveSongPic(String pic,int id);
    @Modifying
    @Transactional
    int deleteSongById(Integer id);
    List<Song> findAllBySingerId(Integer singerId);
    List<Song> findAllByNameIsLike(String name);

    List<Song> findSongsByTypes_id(Integer id);

    @Query("select id from Song ")
    List<Integer> findAllSongId();

    List<Song> findAllByLyricUrlIsNotNull();
}
