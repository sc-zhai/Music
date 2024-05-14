package haut.zsc.music.algorithm.CollaborativeFilter;

import com.google.common.collect.Sets;
import haut.zsc.music.audio.CosineSimilarity;
import haut.zsc.music.entity.Song;
import haut.zsc.music.entity.SongTypesNumber;
import haut.zsc.music.repository.SongRepository;
import haut.zsc.music.repository.SongTypesNumberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BasedUser {

    @Autowired
    private SongTypesNumberRepository songTypesNumberRepository;
    @Autowired
    private SongRepository songRepository;
    public List<Song> recommend(Integer id, int topN) {
        List<SongTypesNumber> songTypesNumberList1 = songTypesNumberRepository.findSongTypesNumbersByUserIdOrderByNumbersDesc(id);
        List<SongTypesNumber> songTypesNumberList2 = songTypesNumberRepository.findAllByUserIdIsNotOrderByUserIdAscNumbersDesc(id);

        //1、得到用户对应的向量vec1
        Map<Integer, Long> vec1 = gernerateVec1(songTypesNumberList1);
        //2、得到所有用户的向量Map，相似度Map降序排列
        Map<Double,Integer> similarityMap=new TreeMap<>(new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                return o2.compareTo(o1);//按照key值，降序排列
            }
        });
        Map<Integer,Map<Integer, Long>> vec2Map=new HashMap<>();
        Map<Integer, Long> vec2=new HashMap<>();
        double similarity=0.0;
        int currentUserId=songTypesNumberList2.get(0).getUserId();
        for(SongTypesNumber stn: songTypesNumberList2){
            //若UserID不变，则为同一人，将type和对应的数量加入map
            if(currentUserId==stn.getUserId()){
                vec2.put(stn.getTypeId(),stn.getNumbers());
            }
            //否则，遍历到下一个人
            else{
                vec2Map.put(currentUserId,vec2);//放入<UserId,<TypeId,Numbers>>
                similarity=CosineSimilarity.cosineSimilarity2(vec1,vec2);//计算相似度
                similarityMap.put(similarity,currentUserId);//放入<similarity,UserId>
                vec2=new HashMap<>();//不能用clear清除，不然会发生引用的错误
                vec2.put(stn.getTypeId(),stn.getNumbers());
                currentUserId=stn.getUserId();//更新UserId
            }
        }
        //把最后一个人的信息添加进去
        vec2Map.put(currentUserId,vec2);//放入<UserId,<TypeId,Numbers>>
        similarity=CosineSimilarity.cosineSimilarity2(vec1,vec2);//计算相似度
        similarityMap.put(similarity,currentUserId);//放入<similarity,UserId>

        //3、得到要推荐的音乐类型的集合：
        Set typesSet=getMusicTypesSet(songTypesNumberList2,vec1,topN);
        System.out.println(typesSet.toString());

        //4、根据类型得到推荐列表
        return null;
    }


    public <S,T> Set<S> getDifferenceListByGuava(Map<S, T> leftMap, Map<S, T> rightMap) {
        if (null != leftMap && null != rightMap) {

            Set<S> leftMapKey = leftMap.keySet();
            Set<S> rightMapKey = rightMap.keySet();
            Set<S> differenceSet = Sets.difference(leftMapKey, rightMapKey);//得到左值中有，而右值中没有的
            return differenceSet;

        } else {
            return new HashSet<S>();
        }

    }

    public Map<Integer, Long> gernerateVec1(List<SongTypesNumber> songTypesNumberList1){
        Map<Integer, Long> vec1 = new HashMap<>();
        songTypesNumberList1.forEach((s)->{
            vec1.put(s.getTypeId(),s.getNumbers());
        });
        return vec1;
    }


    /**
    * @method getMusicTypesSet
    * @return Set
    * @description
    * @date 2024/5/11 17:09
    */
    public  Set getMusicTypesSet(List<SongTypesNumber> songTypesNumberList2,Map<Integer, Long> vec1,int topK){
        //2、得到所有用户的向量Map，相似度Map降序排列
        Map<Double,Integer> similarityMap=new TreeMap<>(new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                return o2.compareTo(o1);//按照key值，降序排列
            }
        });
        Map<Integer,Map<Integer, Long>> vec2Map=new HashMap<>();
        Map<Integer, Long> vec2=new HashMap<>();
        double similarity=0.0;
        int currentUserId=songTypesNumberList2.get(0).getUserId();

        for(SongTypesNumber stn: songTypesNumberList2){
            //若UserID不变，则为同一人，将type和对应的数量加入map
            if(currentUserId==stn.getUserId()){
                vec2.put(stn.getTypeId(),stn.getNumbers());
            }
            //否则，遍历到下一个人
            else{
                vec2Map.put(currentUserId,vec2);//放入<UserId,<TypeId,Numbers>>
                similarity=CosineSimilarity.cosineSimilarity2(vec1,vec2);//计算相似度
                similarityMap.put(similarity,currentUserId);//放入<similarity,UserId>
                vec2=new HashMap<>();//不能用clear清除，不然会发生引用的错误
                vec2.put(stn.getTypeId(),stn.getNumbers());
                currentUserId=stn.getUserId();//更新UserId
            }
        }
        //把最后一个人的信息添加进去
        vec2Map.put(currentUserId,vec2);//放入<UserId,<TypeId,Numbers>>
        similarity=CosineSimilarity.cosineSimilarity2(vec1,vec2);//计算相似度
        similarityMap.put(similarity,currentUserId);//放入<similarity,UserId>

        System.out.println("用户的相似度");
        System.out.println(similarityMap.toString());
        //3、得到要推荐的音乐类型的集合：
        int userIndex=1;
        Set typesSet=new HashSet<>();
        for(Double key:similarityMap.keySet()){
            if(userIndex>topK) break;
            Map<Integer, Long> vec_2 = vec2Map.get(similarityMap.get(key));
            Set temp=getDifferenceListByGuava(vec_2,vec1);//按照key做差
            //if(temp.size()==0) break;//temp大小为0，则可以提前终止
            typesSet=Sets.union(typesSet,temp);
        }
        return typesSet;
    }
    public List<Song> recommendMusic(Set<Integer> typesSet,int recNumber){
        List<Song> songList=new ArrayList<>();
        Map<Integer,List<Song>> allRecSong=new HashMap<>();
        Set<Song> recSong=new HashSet<>();
        for (Integer typeId : typesSet) {
            songList=songRepository.findSongsByTypes_id(typeId);
            if(songList!=null) allRecSong.put(typeId,songList);
        }
        int num_type=allRecSong.size();
        int numPerType=recNumber/num_type;

        for(List<Song> songs:allRecSong.values()){
            for (int i=0;i<numPerType&&i<songs.size();i++)
                recSong.add(songs.get(i));
        }
        return null;

    }
}

