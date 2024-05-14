package haut.zsc.music.service.Impl;

import haut.zsc.music.entity.User;
import haut.zsc.music.repository.UserRepository;
import haut.zsc.music.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * 新增用户
     */
    @Override
    public boolean updateUser(User user) {
        return userRepository.saveOrUpdate(user);
    }

    @Override
    public boolean existUser(String username) {
        return userRepository.existsConsumerByUsername(username);
    }

    @Override
    public boolean veritypasswd(String username, String password) {

        return userRepository.existsConsumerByUsernameAndPassword(username, password);
    }

    // 删除用户
    @Override
    public boolean deleteUser(Integer id) {
        return userRepository.deleteConsumerById(id) > 0 ? true : false;
    }

    @Override
    public List<User> allUser() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User loginStatus(String username) {
        return userRepository.findConsumerByUsername(username).get();
    }

    @Override
    public List<Integer> findAllUserId() {
        return userRepository.findAllUserId();
    }
}
