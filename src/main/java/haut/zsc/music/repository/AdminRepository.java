package haut.zsc.music.repository;

import haut.zsc.music.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer> {
    boolean existsByNameAndPassword(String name, String password);
}
