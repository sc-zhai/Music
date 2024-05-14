package haut.zsc.music.service;

import haut.zsc.music.entity.SongList;

import java.util.List;

public interface SongListService {

    boolean addSongList (SongList songList);

    boolean updateSongListMsg(String title,String introduction,String style, int id);

    boolean updateSongListImg(String pic, int id);

    boolean deleteSongList(Integer id);

    List<SongList> allSongList();

    List<SongList> likeTitle(String title);

    List<SongList> likeStyle(String style);
}
