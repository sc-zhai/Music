package haut.zsc.music.service.Impl;

import haut.zsc.music.entity.Collect;
import haut.zsc.music.entity.Song;
import haut.zsc.music.repository.CollectRepository;
import haut.zsc.music.repository.SongRepository;
import haut.zsc.music.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CollectServiceImpl implements CollectService {
    @Autowired
    private CollectRepository collectRepository;
    @Autowired
    private SongRepository songRepository;
    @Override
    public boolean addCollection(Collect collect) {
        return collectRepository.insertSelective(collect);
    }
    @Override
    public boolean existSongId(Integer userId, Integer songId) {
        return collectRepository.existsByUserIdAndSongId(userId, songId);
    }
    @Override
    public boolean deleteCollect(Integer userId, Integer songId) {
        return collectRepository.deleteByUserIdAndSongId(userId, songId) > 0 ? true : false;
    }
    @Override
    public List<Collect> collectionOfUser(Integer userId) {
        return collectRepository.findCollectsByUserId(userId);
    }

    @Override
    public List<Song> songsOfUser(Integer userId) {
        List<Collect> collects=collectRepository.findCollectsByUserId(userId);
        List<Song> songs=new ArrayList<>();
        for(Collect collect:collects){
            songs.add(songRepository.findById(collect.getSongId()).get());
        }
        return songs;
    }

    @Override
    public List<Collect> findAll() {
        return collectRepository.findAll();
    }
}
