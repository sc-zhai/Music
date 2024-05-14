//package haut.zsc.music.algorithm.network;
//
//import haut.zsc.music.algorithm.CollaborativeFiltering;
//import haut.zsc.music.algorithm.DataTranslate;
//import haut.zsc.music.algorithm.KNN.UserKNN;
//import haut.zsc.music.entity.Collect;
//import haut.zsc.music.entity.DownloadRecord;
//import haut.zsc.music.entity.PlayRecord;
//import haut.zsc.music.service.*;
//import haut.zsc.music.utils.Static;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Repository;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.TimerTask;
//import java.util.concurrent.TimeUnit;
//
//@Component
//
//
//@Repository
//public class UpdateTask extends TimerTask{
//	@Autowired
//	private PersonalRecService personalRecService;
//	@Autowired
//	private DownloadRecordService downloadRecordService;
//	@Autowired
//	private PlayRecordService playRecordService;
//	@Autowired
//	private CollectService collectService;
//	@Autowired
//	private UserService userService;
//	@Autowired
//	private SongService songService;
//
//
//	@Override
//	public void run() {
//		System.out.println("------------开始执行任务-------------");
//
//		try {
//			//等待10s再开始执行任务
//			TimeUnit.SECONDS.sleep(10);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		userService.allUser();
//		//更换推荐列表
//		Static.isFromA=!Static.isFromA;
//		//用户-歌曲 推荐列表
//		Map<Integer,Integer[]> user2songRecMatrix=new HashMap<Integer, Integer[]>();
//		//获取用户的下载记录
//		List<DownloadRecord> downloadList=downloadRecordService.findAll();
//		//获取用户的播放记录
//		List<PlayRecord> playList=playRecordService.findAll();
//		//获取用户的收藏记录
//		List<Collect> collectionList=collectService.findAll();
//		//获取用户ID列表
//		List<Integer> userIdList=userService.findAllUserId();
//		//获取歌曲ID列表
//		List<Integer> songIdList=songService.findAllSongId();
//		//用户-歌曲 “评分”矩阵
//		Map<Integer, float[]> user2songRatingMatrix= DataTranslate.getFrequencyMatrix(userIdList,songIdList,
//				downloadList,playList,collectionList);
//		//用户相似性计算，获取用户的k个近邻用户
//		Map<Integer,Integer[]> userKNNMatrix= UserKNN.getKNN(userIdList,user2songRatingMatrix, Static.K);
//		//基于用户相似性的协同过滤
//		user2songRecMatrix= CollaborativeFiltering.userKNNBasedCF(userIdList,userKNNMatrix,
//				user2songRatingMatrix,songIdList,Static.N);
//		System.out.println("------------执行任务完成-------------");
//		if(Static.isFromA) {
//			//向B中更新写数据
//			personalRecService.updatePersonalRecIntoB(user2songRecMatrix);
//		}else {
//			//向A中更新写数据
//			personalRecService.updatePersonalRecIntoA(user2songRecMatrix);
//		}
//	}
//
//}
