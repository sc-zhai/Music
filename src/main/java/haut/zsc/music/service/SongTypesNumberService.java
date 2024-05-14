package haut.zsc.music.service;

import haut.zsc.music.entity.SongTypesNumber;

import java.util.List;

public interface SongTypesNumberService {
    List<SongTypesNumber> findAll();
    List<SongTypesNumber> findAllOrderByUserIdAndNumbers();
}
