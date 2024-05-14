package haut.zsc.music.service;

import haut.zsc.music.entity.Collect;
import haut.zsc.music.entity.Song;

import java.util.List;

public interface CollectService {

    boolean addCollection(Collect collect);

    boolean existSongId(Integer userId, Integer songId);

    boolean deleteCollect(Integer userId, Integer songId);
    List<Collect> collectionOfUser(Integer userId);
    List<Song> songsOfUser(Integer userId);

    List<Collect> findAll();
}
