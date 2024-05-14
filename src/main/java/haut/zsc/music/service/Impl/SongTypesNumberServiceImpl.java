package haut.zsc.music.service.Impl;

import haut.zsc.music.entity.SongTypesNumber;
import haut.zsc.music.repository.SongTypesNumberRepository;
import haut.zsc.music.service.SongTypesNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SongTypesNumberServiceImpl implements SongTypesNumberService {
    @Autowired
    SongTypesNumberRepository songTypesNumberRepository;
    @Override
    public List<SongTypesNumber> findAll() {
        return songTypesNumberRepository.findAll();
    }

    @Override
    public List<SongTypesNumber> findAllOrderByUserIdAndNumbers() {
        return songTypesNumberRepository.findAllByUserIdIsNotOrderByUserIdAscNumbersDesc(1);
    }
}

