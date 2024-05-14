package haut.zsc.music.algorithm.RecommendStrategy;

import haut.zsc.music.entity.Song;
import haut.zsc.music.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PersonalRecommend implements RecommendStrategy{
    @Autowired
    private SongRepository songRepository;
    @Override
    public Set<Song> recommend(Set<Integer> typesSet, int recCount) {
        List<Song> songList;
        Map<Integer,List<Song>> allRecSong=new HashMap<>();
        Set<Song> recSong=new HashSet<>();
        for (Integer typeId : typesSet) {
            songList=songRepository.findSongsByTypes_id(typeId);
            if(songList!=null) allRecSong.put(typeId,songList);
        }
        int num_type=allRecSong.size();
        int numPerType=recCount/num_type;
        for(List<Song> songs:allRecSong.values()){
            for (int i=0;i<numPerType&&i<songs.size();i++)
                recSong.add(songs.get(i));
        }
        return recSong;
    }
}

