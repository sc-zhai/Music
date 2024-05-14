package haut.zsc.music.service.Impl;

import haut.zsc.music.entity.Comment;
import haut.zsc.music.repository.CommentRepository;
import haut.zsc.music.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public boolean addComment(Comment comment) {
        int flag=commentRepository.save(comment).getId();
        return flag > 0 ? true:false;
    }

    @Override
    public boolean updateCommentMsg(Comment comment) {
        int flag=commentRepository.updateUp(comment.getId(),comment.getUp());
        return flag > 0 ? true:false;
    }

//    删除评论
    @Override
    public boolean deleteComment(Integer id) {
        return commentRepository.deleteCommentById(id) > 0 ? true : false;
    }

    @Override
    public List<Comment> commentOfSongId(Integer songId)

    {
        return commentRepository.findAllBySongId(songId);
    }

    @Override
    public List<Comment> commentOfSongListId(Integer songListId) {
        return commentRepository.findAllBySongListId(songListId);
    }
}
