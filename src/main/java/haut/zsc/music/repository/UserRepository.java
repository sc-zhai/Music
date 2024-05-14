package haut.zsc.music.repository;

import haut.zsc.music.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    default boolean saveOrUpdate(User user){
        int flag=save(user).getId();
        return flag > 0 ? true : false;
    }
    boolean existsConsumerByUsernameAndPassword(String username ,String password);
    Optional<User> findConsumerByUsername(String username);
    boolean existsConsumerByUsername(String username);
    @Modifying
    @Transactional
    int deleteConsumerById(Integer Id);

    @Query("select id from User")
    List<Integer> findAllUserId();
}
