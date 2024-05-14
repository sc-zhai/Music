package haut.zsc.music;

import haut.zsc.music.algorithm.CollaborativeFilter.BasedUser;
import haut.zsc.music.common.Message;
import haut.zsc.music.entity.User;
import haut.zsc.music.repository.AdminRepository;
import haut.zsc.music.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MusicZApplicationTests {
@Autowired
	AdminRepository adminRepository;
	@Autowired
	UserRepository userRepository;
	@Test
	void contextLoads() {
		User user = userRepository.findById(1).get();

		Message<User> message=new Message<>("success",500,false,"error", user);
		System.out.println(message.getMessage());
	}

}
