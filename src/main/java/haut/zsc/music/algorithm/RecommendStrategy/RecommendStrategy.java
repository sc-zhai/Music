package haut.zsc.music.algorithm.RecommendStrategy;

import haut.zsc.music.entity.Song;

import java.util.Set;

public interface RecommendStrategy {
    Set<Song>  recommend(Set<Integer> typesSet,int recCount);
}
