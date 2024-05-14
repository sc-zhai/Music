package haut.zsc.music.algorithm.RecommendStrategy;

import haut.zsc.music.entity.Song;

import java.util.Set;

public class Recommend {
    private RecommendStrategy recommendStrategy;
    public Set<Song> recommend(Set<Integer> typesSet, int recCount){
        return recommendStrategy.recommend(typesSet, recCount);
    }
}

