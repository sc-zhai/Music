package haut.zsc.music.repository;

import haut.zsc.music.entity.PlayRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayRecordRepository extends JpaRepository<PlayRecord, Integer> {
}
