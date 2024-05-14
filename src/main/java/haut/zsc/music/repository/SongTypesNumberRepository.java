package haut.zsc.music.repository;

import haut.zsc.music.entity.SongTypesNumber;
import org.springframework.data.jpa.repository.JpaRepository;



import java.util.List;
public interface SongTypesNumberRepository extends JpaRepository<SongTypesNumber,Integer> {

//    @Query(value = "select * from SongTypes order by userId asc,numbers desc ",nativeQuery = true)
    List<SongTypesNumber> findSongTypesNumbersByUserIdOrderByNumbersDesc(Integer id);

    List<SongTypesNumber> findAllByUserIdIsNotOrderByUserIdAscNumbersDesc(Integer id);
}
