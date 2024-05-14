package haut.zsc.music.service.Impl;

import haut.zsc.music.repository.AdminRepository;
import haut.zsc.music.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public boolean veritypasswd(String name, String password) {
        return adminRepository.existsByNameAndPassword(name,password);
    }
}
