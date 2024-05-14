package haut.zsc.music.service;

import haut.zsc.music.entity.User;

import java.util.List;

public interface UserService {

    boolean updateUser(User user);

    boolean existUser(String username);

    boolean veritypasswd(String username, String password);

    boolean deleteUser(Integer id);

    List<User> allUser();

    User findById(Integer id);

    User loginStatus(String username);
    List<Integer> findAllUserId();

}
