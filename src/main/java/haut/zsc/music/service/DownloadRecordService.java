package haut.zsc.music.service;

import haut.zsc.music.entity.DownloadRecord;

import java.util.List;

public interface DownloadRecordService {

    List<DownloadRecord> findAll();
}
