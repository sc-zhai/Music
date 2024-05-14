package haut.zsc.music.service;

import haut.zsc.music.entity.Song;
import haut.zsc.music.entity.Type;

import java.util.List;
import java.util.Optional;

public interface SongService {

    boolean addSong (Song song);

    boolean updateSongMsg(String name,String introduction,String lyric,int id);

    boolean updateSongUrl(Song song);

    boolean updateSongPic(String pic ,int id);

    boolean deleteSong(Integer id);

    List<Song> allSong();

    List<Song> songOfSingerId(Integer singerId);

    Optional<Song> songOfId(Integer id);

    List<Song> songOfSingerName(String name);
    List<Song> findType_Songs(Integer id);
    List<Type> findallTpyes();
    List<Song> recommend(Integer id);
    List<Song> dailyRecommend(Integer id);

    List<Integer> findAllSongId();

    List<Song> findAllSongWithLyricUrl();
}
