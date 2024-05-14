package haut.zsc.music.controller;

import haut.zsc.music.common.ErrorMessage;
import haut.zsc.music.common.SuccessMessage;
import haut.zsc.music.entity.ListSong;
import haut.zsc.music.service.Impl.ListSongServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ListSongController {

    @Autowired
    private ListSongServiceImpl listSongService;

    // 给歌单添加歌曲
    @ResponseBody
    @RequestMapping(value = "/listSong/add", method = RequestMethod.POST)
    public Object addSongToList(HttpServletRequest req) {
        String song_id = req.getParameter("songId").trim();
        String song_list_id = req.getParameter("songListId").trim();

        ListSong listsong = new ListSong();
        listsong.setSongId(Integer.parseInt(song_id));
        listsong.setSongListId(Integer.parseInt(song_list_id));

        boolean res = listSongService.addListSong(listsong);
        if (res) {
            return new SuccessMessage<Null>("添加成功").getMessage();
        } else {
            return new ErrorMessage("添加失败").getMessage();
        }
    }

    // 删除歌单里的歌曲
    @RequestMapping(value = "/listSong/delete", method = RequestMethod.GET)
    public Object deleteListSong(HttpServletRequest req) {
        String songId = req.getParameter("songId");
        System.out.println("hahahahahahah"+songId);
        boolean res = listSongService.deleteListSong(Integer.parseInt(songId));
        if (res) {
            return new SuccessMessage<Null>("删除成功").getMessage();
        } else {
            return new ErrorMessage("删除失败").getMessage();
        }
    }

    // 返回歌单里指定歌单 ID 的歌曲
    @RequestMapping(value = "/listSong/detail", method = RequestMethod.GET)
    public Object listSongOfSongId(HttpServletRequest req) {
        String songListId = req.getParameter("songListId");

        return new SuccessMessage<List<ListSong>>("添加成功", listSongService.listSongOfSongId(Integer.parseInt(songListId)))
                .getMessage();
    }

}
