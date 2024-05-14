package haut.zsc.music.service.Impl;

import haut.zsc.music.entity.BehaviorForType;
import haut.zsc.music.entity.Song;
import haut.zsc.music.entity.Type;
import haut.zsc.music.repository.RecTypeRepository;
import haut.zsc.music.repository.SongRepository;
import haut.zsc.music.repository.TypeRepository;
import haut.zsc.music.repository.UserRepository;
import haut.zsc.music.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private SongRepository songRepository;
    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecTypeRepository recTypeRepository;

    @Override
    public List<Song> allSong() {
        return songRepository.findAll();
    }

    @Override
    public boolean addSong(Song song) {

        return songRepository.insertSelective(song);
    }

    @Override
    public boolean updateSongMsg(String name,String introduction,String lyric,int id) {
        return songRepository.saveSongMsg(name,introduction,lyric,id)>0;
    }

    @Override
    public boolean updateSongUrl(Song song) {

        return songRepository.saveOrUpdate(song);
    }

    @Override
    public boolean updateSongPic(String pic ,int id) {

        return songRepository.saveSongPic(pic,id)>0;
    }

    @Override
    public boolean deleteSong(Integer id) {
        return songRepository.deleteSongById(id) > 0 ? true : false;
    }
    @Override
    public List<Song> songOfSingerId(Integer singerId) {
        return songRepository.findAllBySingerId(singerId);
    }

    @Override
    public Optional<Song> songOfId(Integer id) {
        return songRepository.findById(id);
    }

    @Override
    public List<Song> songOfSingerName(String name) {
        return songRepository.findAllByNameIsLike(name);
    }

    @Override
    public List<Song> findType_Songs(Integer id) {
        return songRepository.findSongsByTypes_id(id);
    }

    @Override
    public List<Type> findallTpyes() {
        return typeRepository.findAll();
    }

    @Cacheable(cacheNames = "recommendMusic",key = "'user_'+#id")
    @Override
    public List<Song> recommend(Integer id) {
        List<Song> songs = this.allSong();
        List<Song> res = new ArrayList<>();
        List<BehaviorForType> behaviors = new ArrayList<>();
        Random random = new Random();
        behaviors = recTypeRepository.findBehaviorForTypesByUser_IdOrderByScoreDesc(id);
        if(behaviors.size()<5){
            for (int i = 0; i < behaviors.size(); i++) {
                List<Song> songList=songRepository.findSongsByTypes_id(behaviors.get(i).getType().getId());
                Song song=songList.get(random.nextInt(0,songList.size()));
                res.add(song);
            }
            int flag;

            for (int i = 0; i < 5-behaviors.size(); i++) {
                flag = random.nextInt(0, songs.size() - 1);
                res.add(songs.get(flag));
                songs.remove(flag);
            }
        }
        else {
            for (int i = 0; i < 5; i++) {
                List<Song> songList=songRepository.findSongsByTypes_id(behaviors.get(i).getType().getId());
                Song song=songList.get(random.nextInt(0,songList.size()));
                res.add(song);
            }
        }
        return res;
    }
    @Cacheable(cacheNames = "dailyRecommend",key = "'user_'+#id")
    @Override
    public List<Song> dailyRecommend(Integer id) {
        Random random = new Random();
        List<Song> res = new ArrayList<>();
        List<Song> songList=songRepository.findAll();
        int flag;
        for (int i = 0; i < 10; i++) {
            flag=random.nextInt(0,songList.size());
            Song song=songList.get(flag);
            res.add(song);
            songList.remove(flag);
        }
        return res;
    }

    @Override
    public List<Integer> findAllSongId() {
        return songRepository.findAllSongId();
    }

    @Override
    public List<Song> findAllSongWithLyricUrl() {
        return songRepository.findAllByLyricUrlIsNotNull();
    }
}
