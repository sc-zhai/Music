package haut.zsc.music.controller;

import haut.zsc.music.common.ErrorMessage;
import haut.zsc.music.common.FatalMessage;
import haut.zsc.music.common.SuccessMessage;
import haut.zsc.music.common.Constants;
import haut.zsc.music.entity.Song;
import haut.zsc.music.entity.Type;
import haut.zsc.music.service.Impl.SongServiceImpl;
import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
public class SongController {

    @Autowired
    private SongServiceImpl songService;

    @Autowired
    public MultipartConfigElement multipartConfigElement;


    @Configuration
    public static class MySongConfig implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/img/songPic/**")
                    .addResourceLocations(Constants.SONG_PIC_PATH);
            registry.addResourceHandler("/song/**")
                    .addResourceLocations(Constants.SONG_PATH);
        }
    }


    // 添加歌曲
    @ResponseBody
    @RequestMapping(value = "/song/add", method = RequestMethod.POST)
    public Object addSong(HttpServletRequest req, @RequestParam("file") MultipartFile mpfile) {
        String singer_id = req.getParameter("singerId").trim();
        String name = req.getParameter("name").trim();
        String introduction = req.getParameter("introduction").trim();
        String pic = "/img/songPic/tubiao.jpg";
        String lyric = req.getParameter("lyric").trim();

        String fileName = mpfile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "song";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }

        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeUrlPath = "/song/" + fileName;
        try {
            mpfile.transferTo(dest);
            Song song = new Song();
            song.setSingerId(Integer.parseInt(singer_id));
            song.setName(name);
            song.setIntroduction(introduction);
            song.setCreateTime(new Date());
            song.setUpdateTime(new Date());
            song.setPic(pic);
            song.setLyric(lyric);
            song.setUrl(storeUrlPath);
            boolean res = songService.addSong(song);
            if (res) {
                return new SuccessMessage<String>("上传成功", storeUrlPath).getMessage();
            } else {
                return new ErrorMessage("上传失败").getMessage();
            }
        } catch (IOException e) {
            return new FatalMessage("上传失败" + e.getMessage()).getMessage();
        }
    }

    // 删除歌曲
    @RequestMapping(value = "/song/delete", method = RequestMethod.GET)
    public Object deleteSong(HttpServletRequest req) {
        String id = req.getParameter("id");

        boolean res = songService.deleteSong(Integer.parseInt(id));
        if (res) {
            return new SuccessMessage<Null>("删除成功").getMessage();
        } else {
            return new ErrorMessage("删除失败").getMessage();
        }
    }

    // 返回所有歌曲
    @RequestMapping(value = "/song", method = RequestMethod.GET)
    public Object findallSong() {
        return new SuccessMessage<List<Song>>(null, songService.allSong()).getMessage();
    }

    // 返回指定歌曲ID的歌曲
    @RequestMapping(value = "/song/detail", method = RequestMethod.GET)
    public Object songOfId(HttpServletRequest req) {
        String id = req.getParameter("id");

        return new SuccessMessage<Song>(null, songService.songOfId(Integer.parseInt(id)).get()).getMessage();
    }

    // 返回指定歌手ID的歌曲
    @RequestMapping(value = "/song/singer/detail", method = RequestMethod.GET)
    public Object findSongBySingerId(HttpServletRequest req) {
        String singerId = req.getParameter("singerId");

        return new SuccessMessage<List<Song>>(null, songService.songOfSingerId(Integer.parseInt(singerId))).getMessage();
    }

    // 返回指定歌手名的歌曲
    @RequestMapping(value = "/song/singerName/detail", method = RequestMethod.GET)
    public Object findSongByText(HttpServletRequest req) {
        String name = req.getParameter("name");

        return new SuccessMessage<List<Song>>(null, songService.songOfSingerName('%' + name + '%')).getMessage();
    }

    // 更新歌曲信息
    @ResponseBody
    @RequestMapping(value = "/song/update", method = RequestMethod.POST)
    public Object updateSongMsg(HttpServletRequest req) {
        String s_id = req.getParameter("id").trim();
        String name = req.getParameter("name").trim();
        String introduction = req.getParameter("introduction").trim();
        String lyric = req.getParameter("lyric").trim();
        int id=Integer.valueOf(s_id);
        boolean res = songService.updateSongMsg(name,introduction,lyric,id);
        if (res) {
            return new SuccessMessage<Null>("修改成功").getMessage();
        } else {
            return new ErrorMessage("修改失败").getMessage();
        }
    }

    // 更新歌曲图片
    @ResponseBody
    @RequestMapping(value = "/song/img/update", method = RequestMethod.POST)
    public Object updateSongPic(@RequestParam("file") MultipartFile urlFile, @RequestParam("id") int id) {
        String fileName = System.currentTimeMillis() + urlFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img" + System.getProperty("file.separator") + "songPic";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }

        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeUrlPath = "img/songPic/" + fileName;
        try {
            urlFile.transferTo(dest);
            boolean res = songService.updateSongPic(storeUrlPath,id);
            if (res) {
                return new SuccessMessage<String>("上传成功", storeUrlPath).getMessage();
            } else {
                return new ErrorMessage("上传失败").getMessage();
            }
        } catch (IOException e) {
            return new FatalMessage("上传失败" + e.getMessage()).getMessage();
        }
    }

    // 更新歌曲
    @ResponseBody
    @RequestMapping(value = "/song/url/update", method = RequestMethod.POST)
    public Object updateSongUrl(@RequestParam("file") MultipartFile urlFile, @RequestParam("id") int id) {
        String fileName = urlFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "song";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }

        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeUrlPath = "/song/" + fileName;
        try {
            urlFile.transferTo(dest);
            Song song = new Song();
            song.setId(id);
            song.setUrl(storeUrlPath);
            boolean res = songService.updateSongUrl(song);
            if (res) {
                return new SuccessMessage<String>("更新成功", storeUrlPath).getMessage();
            } else {
                return new ErrorMessage("更新失败").getMessage();
            }
        } catch (IOException e) {
            return new FatalMessage("更新失败" + e.getMessage()).getMessage();
        }
    }

    // 返回推荐的音乐
    @RequestMapping(value = "/song/recommend", method = RequestMethod.GET)
    public Object personalRecommend(HttpServletRequest req) {
        String id = req.getParameter("id");
        SuccessMessage message=new SuccessMessage<List<Song>>("获取成功", songService.recommend(Integer.valueOf(id)));
        return message.getMessage();
    }
    // 返回推荐的音乐
    @RequestMapping(value = "/song/refreshRecommend", method = RequestMethod.GET)
    public Object refreshRecommend(HttpServletRequest req) {
        String id = req.getParameter("id");
        SuccessMessage message=new SuccessMessage<List<Song>>("获取成功", songService.recommend(Integer.valueOf(id)));
        return message.getMessage();
    }
    // 返回日常推荐的音乐
    @RequestMapping(value = "/song/dailyRecommend", method = RequestMethod.GET)
    public Object dailyRecommend(HttpServletRequest req) {
        String s_id = req.getParameter("id");
        Integer id;
        if(s_id==null||s_id.equals(""))id=-1;
        else id=Integer.valueOf(s_id);
        SuccessMessage message=new SuccessMessage<List<Song>>("获取成功", songService.dailyRecommend(id));
        return message.getMessage();
    }

    // 返回推荐的音乐
    @RequestMapping(value = "/song/types", method = RequestMethod.GET)
    public Object findAllTypes(HttpServletRequest req) {
        SuccessMessage message=new SuccessMessage<List<Type>>("获取成功", songService.findallTpyes());
        return message.getMessage();
    }
    @RequestMapping(value = "/song/type_songs", method = RequestMethod.GET)
    public Object findType_Songs(HttpServletRequest req) {
        String s_id = req.getParameter("id");
        int id=Integer.valueOf(s_id);
        List<Song> songs=songService.findType_Songs(id);
        SuccessMessage<List<Song>> message = new SuccessMessage<List<Song>>("获取成功",songs);
        return message.getMessage();
    }
}
