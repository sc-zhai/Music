//package haut.zsc.music.service.Impl;
//
//import com.haut.music.dao.NewTrackOnShelfDao;
//import com.haut.music.dao.PersonalRecDao;
//import com.haut.music.dao.TrendingRecDao;
//
//import haut.zsc.music.entity.Collect;
//import haut.zsc.music.entity.Song;
//import haut.zsc.music.entity.User;
//import haut.zsc.music.repository.CollectRepository;
//import haut.zsc.music.repository.SongRepository;
//import haut.zsc.music.repository.UserRepository;
//import haut.zsc.music.service.PersonalRecService;
//import haut.zsc.music.utils.Static;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.*;
//import java.util.function.BiConsumer;
//
//@Service("personalRecService")
//public class PersonalRecServiceImpl implements PersonalRecService {
//	@Autowired
//	private PersonalRecDao personalRecDao;
//	@Autowired
//	private UserRepository userRepository;
////	@Autowired
////	private TrendingRecDao trendingRecDao;
//	@Autowired
//	CollectRepository collectRepository;
//
////	@Autowired
////	private NewTrackOnShelfDao newTrackOnShelfDao; //新歌列表
//	@Autowired
//	private SongRepository songRepository;
//
//
//	public List<Song> getPersonalDailyRecWithCollectionFlag(Integer id) {
//		List<Song> personalRecList = new ArrayList<Song>();
//		List<Collect> collectList = new ArrayList<>();
//		Optional<User> userOptional = userRepository.findById(id);
//		User user=userOptional.get();
//		collectList = collectRepository.findCollectsByUserId(id);
//		/* =============================================================== */
//		personalRecList=selectPersonalRec(user);
//		/* =============================================================== */
//		// 在个性化列表中给已经被该用户收藏的歌曲加上标记
//		if (collectList != null && personalRecList != null) {
//			for (Collect c : collectList) {
//				for (Song t : personalRecList) {
//					if (c.getSongId() == t.getId()) {
//						t.setWhetherCollected(true);
//					}
//				}
//			}
//		}
//		return personalRecList;
//	}
//
//	/**
//	 * 每天早上6点更新个性化推荐列表，从更新后的表中读取记录
//	 * 这里采用两张表交替的方式来实现：
//	 * （1）	6点之后就从另外一张表中读取记录
//	 * （2）	重新开始计算新的个性化推荐列表存放于原来的表中的
//	 * @param user
//	 * @return
//	 */
//	private List<Song> selectPersonalRec(User user) {
//		if(user==null) return null;
//		List<Song> personalRecList = new ArrayList<Song>();
//		if(Static.isFromA) {
//			personalRecList=personalRecDao.selectPersonalRecFromA(user);
//		}else {
//			personalRecList=personalRecDao.selectPersonalRecFromB(user);
//		}
//		return personalRecList;
//	}
//
//	public void initializePersonalRecList(Integer id) {
//		final Optional<User> userOptional=userRepository.findById(id);
//		final User user=userOptional.get();
//		List<Song> initialRecListA = new ArrayList<>();
//		List<Song> initialRecListB = new ArrayList<>();
//		//从新歌中随机获取10首，作为初始化列表
//		initialRecListA=;
//		for(int i=0;i<40;i++) {
//			int len=initialRecListA.size();
//			Random random=new Random();
//			int index=random.nextInt(len);
//			if(i<10) {
//				initialRecListB.add(initialRecListA.get((index+1)%len));
//			}
//			initialRecListA.remove(index);
//		}
//		//批量插入
//		if(Static.isFromA) {
//			personalRecDao.insertListIntoRecA(initialRecListA,user.getUserId());
//		}else {
//			personalRecDao.insertListIntoRecB(initialRecListB,user.getUserId());
//		}
//
//	}
//
//	public void updatePersonalRecIntoB(Map<Integer, Integer[]> user2song) {
//		// TODO Auto-generated method stub
//		user2song.forEach(new BiConsumer<Integer,Integer[]>(){
//
//			public void accept(Integer userId, Integer[] recSongIds) {
//				// TODO Auto-generated method stub
//				personalRecDao.deleteBByUserId(userId);
//				//批量插入
//				personalRecDao.insertArrayIntoRecB(recSongIds,userId);
//
//			}
//
//		});
//
//	}
//
//	public void updatePersonalRecIntoA(Map<Integer, Integer[]> user2song) {
//		// TODO Auto-generated method stub
//		user2song.forEach(new BiConsumer<Integer,Integer[]>(){
//
//			public void accept(Integer userId, Integer[] recSongIds) {
//				// TODO Auto-generated method stub
//				personalRecDao.deleteAByUserId(userId);
//				//批量插入
//				personalRecDao.insertArrayIntoRecA(recSongIds,userId);
//
//			}
//
//		});
//
//	}
//
//	public void addHybridRecIntoA(Map<Integer, Integer[]> user2song) {
//		user2song.forEach(new BiConsumer<Integer,Integer[]>(){
//
//			public void accept(Integer userId, Integer[] recSongIds) {
//				//批量插入
//				personalRecDao.insertArrayIntoRecA(recSongIds,userId);
//			}
//
//		});
//
//	}
//
//	public void addHybridRecIntoB(Map<Integer, Integer[]> user2song) {
//		user2song.forEach(new BiConsumer<Integer,Integer[]>(){
//
//			public void accept(Integer userId, Integer[] recSongIds) {
//				//批量插入
//				personalRecDao.insertArrayIntoRecB(recSongIds,userId);
//			}
//
//		});
//
//	}
//
//
//
//}
