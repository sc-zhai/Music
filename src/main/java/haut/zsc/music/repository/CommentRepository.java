package haut.zsc.music.repository;

import haut.zsc.music.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
    List<Comment> findAllBySongId(Integer songId);
    List<Comment> findAllBySongListId(Integer songListId);
    @Modifying
    @Transactional
    int deleteCommentById(Integer Id);
    @Modifying
    @Transactional
    @Query("update Comment c set c.up=:up where c.id=:id ")
    int updateUp(Integer id,Integer up);

}
