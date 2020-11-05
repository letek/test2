package pl.sda.javatarr6.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.sda.javatarr6.demo.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    //List<User> getAllByDate(Date date);

    Optional<User> findByUsername(String username);

    //Optional<>
    //User findByUsername(String username);
}