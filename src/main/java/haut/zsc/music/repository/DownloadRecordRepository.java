package haut.zsc.music.repository;

import haut.zsc.music.entity.DownloadRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DownloadRecordRepository extends JpaRepository<DownloadRecord,Integer> {
}

