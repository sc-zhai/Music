package haut.zsc.music.service.Impl;

import haut.zsc.music.entity.DownloadRecord;
import haut.zsc.music.repository.DownloadRecordRepository;
import haut.zsc.music.service.DownloadRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DownloadRecordImpl implements DownloadRecordService {
    @Autowired
    DownloadRecordRepository downloadRecordRepository;

    @Override
    public List<DownloadRecord> findAll() {
        return downloadRecordRepository.findAll();
    }
}

