package haut.zsc.music;

import haut.zsc.music.algorithm.CollaborativeFilter.BasedUser;
import haut.zsc.music.entity.Collect;
import haut.zsc.music.entity.Song;
import haut.zsc.music.entity.Type;
import haut.zsc.music.entity.User;
import haut.zsc.music.repository.*;
import haut.zsc.music.service.SongTypesNumberService;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
@SpringBootTest
public class DataBaseTest {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private TypeRepository typeRepository;

  @Autowired
  private SongRepository songRepository;

  @Autowired
  private CollectRepository collectRepository;

  @Autowired
  private RecTypeRepository recTypeRepository;

  @Autowired
  SongTypesNumberService songTypesNumberService;

  @Autowired
  SongTypesNumberRepository songTypesNumberRepository;
  @Autowired
  private BasedUser basedUser;


  @Test
  void rec() {
    songTypesNumberRepository.findSongTypesNumbersByUserIdOrderByNumbersDesc(1).forEach((s) -> System.out.println(s.toString()));
    System.out.println("分割线");
    songTypesNumberService.findAllOrderByUserIdAndNumbers().forEach(s -> System.out.println(s.toString()));

    Map<String, Integer> map = new HashMap<>();
    map.put("A", 20);
    map.put("B", 30);
    map.put("A", 40);
    System.out.println(map);

  }


  @Test
  void testSimilarity(){
    basedUser.recommend(1,5);
  }
}


