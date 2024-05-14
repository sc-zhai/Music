package haut.zsc.music.service.Impl;

import haut.zsc.music.entity.Singer;
import haut.zsc.music.repository.SingerRepository;
import haut.zsc.music.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SingerServiceImpl implements SingerService {

    @Autowired
    private SingerRepository singerRepository;

    @Override
    public boolean updateSingerMsg(Singer singer) {
        return singerRepository.saveOrUpdate(singer);
    }
    @Transactional
    @Override
    public boolean updateSingerPic(String url,int id) {
        return singerRepository.saveSingerPic(url,id)>0;
    }

    @Override
    public boolean deleteSinger(Integer id) {
        return singerRepository.deleteSingerById(id) > 0 ? true : false;
    }

    @Override
    public List<Singer> allSinger() {
        return singerRepository.findAll();
    }

    @Override
    public boolean addSinger(Singer singer)  {

        return singerRepository.insertSelective(singer);
    }

    @Override
    public List<Singer> singerOfName(String name)

    {
        return singerRepository.findAllByNameIsLike(name);
    }

    @Override
    public List<Singer> singerOfSex(Integer sex) {
        return singerRepository.findAllBySex(sex);
    }
}
