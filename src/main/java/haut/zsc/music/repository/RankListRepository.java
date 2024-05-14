package haut.zsc.music.repository;

import haut.zsc.music.entity.RankList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RankListRepository extends JpaRepository<RankList,Integer> {
    default boolean saveOrUpdate(RankList rankList){
        long flag=save(rankList).getId();
        return flag > 0 ? true : false;
    }
    int countAllBySongListId(Long songId);
    @Query("SELECT COALESCE(sum(score),0) as score from RankList where songListId = :songListId ")
    int selectScoreSum(Long songListId);

    @Query("SELECT ifnull(score,0) from RankList where songListId = :songListId and userId = :userId")

    int selectUserRank(Long userId, Long songListId);
}
