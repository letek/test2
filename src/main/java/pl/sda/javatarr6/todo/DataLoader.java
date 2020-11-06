package pl.sda.javatarr6.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pl.sda.javatarr6.todo.entity.User;
import pl.sda.javatarr6.todo.entity.ZadanieEntity;
import pl.sda.javatarr6.todo.repository.UserRepository;
import pl.sda.javatarr6.todo.repository.ZadanieRepository;

import java.util.Date;

@Component
public class DataLoader implements ApplicationRunner {


    @Autowired
    private ZadanieRepository zadanieRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) throws  Exception {

//        User user = new User("q@q.com","$2a$10$35zYo4fHJ/cjP3QPfMEyDeLSaClFeg1mT1C9q5yMqGl85ccQ/twWO","q@q.com");
//        userRepository.save(user);
//        User user2 = new User("a@a.com","$2a$10$35zYo4fHJ/cjP3QPfMEyDeLSaClFeg1mT1C9q5yMqGl85ccQ/twWO","a@a.com");
//        userRepository.save(user2);

        ZadanieEntity zadanieEntity = new ZadanieEntity("jzadanie 1", new Date() , new Date(), new Boolean(true),new User(1L));
        zadanieRepository.save(zadanieEntity);
        ZadanieEntity zadanieEntity2 = new ZadanieEntity("ToDo cos tam",new Date(), null, new Boolean(false),new User(11L) );
        zadanieRepository.save(zadanieEntity2);
        ZadanieEntity zadanieEntity3 = new ZadanieEntity("Lalala",new Date(), null , new Boolean(false),new User(1L) );
        zadanieRepository.save(zadanieEntity3);
        ZadanieEntity zadanieEntity4 = new ZadanieEntity("Nowe Zadanie",new Date(), null , new Boolean(false),new User(11L) );
        zadanieRepository.save(zadanieEntity4);

    }
}
