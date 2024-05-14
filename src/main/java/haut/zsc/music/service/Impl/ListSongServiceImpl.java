package haut.zsc.music.service.Impl;

import haut.zsc.music.entity.ListSong;
import haut.zsc.music.repository.ListSongRepository;
import haut.zsc.music.service.ListSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ListSongServiceImpl implements ListSongService {

    @Autowired
    private ListSongRepository listSongRepository;

    @Override
    public List<ListSong> allListSong()
    {
        return listSongRepository.findAll();
    }

    @Override
    public boolean updateListSongMsg(ListSong listSong) {
        return listSongRepository.saveOrUpdate(listSong);
    }
   @Transactional
    @Override
    public boolean deleteListSong(Integer songId) {
        if(listSongRepository.existsById(songId)){
            listSongRepository.deleteById(songId);
            return true;
        }
        return false;
    }

    @Override
    public boolean addListSong(ListSong listSong) {
        int flag=listSongRepository.save(listSong).getId();
        return flag > 0 ? true:false;
    }

    @Override
    public List<ListSong> listSongOfSongId(Integer songListId)
    {
        return listSongRepository.findAllBySongListId(songListId);
    }

}
