package haut.zsc.music.service.Impl;

import haut.zsc.music.entity.SongList;
import haut.zsc.music.repository.SongListRepository;
import haut.zsc.music.service.SongListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongListServiceImpl implements SongListService {

    @Autowired
    private SongListRepository songListRepository;

    @Override
    public boolean updateSongListMsg(String title,String introduction,String style, int id) {
        return songListRepository.saveSongListMsg(title,introduction,style, id)>0;
    }

    @Override
    public boolean deleteSongList(Integer id) {
        return songListRepository.deleteSongListById(id) >0 ?true:false;
    }

    @Override
    public List<SongList> allSongList()
    {
        return songListRepository.findAll();
    }

    @Override
    public List<SongList> likeTitle(String title)
    {
        return songListRepository.findAllByTitleIsLike(title);
    }

    @Override
    public List<SongList> likeStyle(String style)
    {
        return songListRepository.findAllByStyleIsLike(style);
    }

    @Override
    public boolean addSongList(SongList songList)
    {
        return songListRepository.insertSelective(songList);
    }

    @Override
    public boolean updateSongListImg(String pic, int id) {

        return songListRepository.saveSongListPic(pic,id)>0;
    }
}
