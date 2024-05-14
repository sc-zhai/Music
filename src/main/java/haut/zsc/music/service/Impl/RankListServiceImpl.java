package haut.zsc.music.service.Impl;

import haut.zsc.music.entity.RankList;
import haut.zsc.music.repository.RankListRepository;
import haut.zsc.music.service.RankListService;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 */
@Service
public class RankListServiceImpl implements RankListService {

    //private static final Logger logger = LogManager.getLogger(RankListServiceImpl.class);

    @Autowired
    private RankListRepository rankRepository;

    @Override
    public boolean addRank(RankList rankList) {
        return rankRepository.saveOrUpdate(rankList);
    }

    @Override
    public int rankOfSongListId(Long songListId) {
        // 评分总人数如果为 0，则返回0；否则返回计算出的结果
        int rankNum = rankRepository.countAllBySongListId(songListId);
        return (rankNum <= 0) ? 0 : rankRepository.selectScoreSum(songListId) / rankNum;
    }

    @Override
    public int getUserRank(Long consumerId, Long songListId) {
        return rankRepository.selectUserRank(consumerId, songListId);
    }
}
