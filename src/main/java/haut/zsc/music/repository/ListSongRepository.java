package haut.zsc.music.repository;

import haut.zsc.music.entity.ListSong;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListSongRepository extends JpaRepository<ListSong,Integer> {
    default boolean saveOrUpdate(ListSong listSong){
        int flag=save(listSong).getId();
        return flag > 0 ? true : false;
    }
    int deleteBySongId(Integer id);
    List<ListSong> findAllBySongListId(Integer songId);
}
