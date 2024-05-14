package haut.zsc.music.service.Impl;

import haut.zsc.music.entity.PlayRecord;
import haut.zsc.music.repository.PlayRecordRepository;
import haut.zsc.music.service.PlayRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayRecordImpl implements PlayRecordService {

    @Autowired
    private PlayRecordRepository playRecordRepository;

    @Override
    public List<PlayRecord> findAll() {
        return playRecordRepository.findAll();
    }
}

