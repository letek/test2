package pl.sda.javatarr6.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pl.sda.javatarr6.demo.entity.ZadanieEntity;
import pl.sda.javatarr6.demo.repository.ZadanieRepository;

import java.util.Calendar;
import java.util.Date;

@Component
public class DataLoader implements ApplicationRunner {


    @Autowired
    private ZadanieRepository zadanieRepository;

    @Override
    public void run(ApplicationArguments args) throws  Exception {

        Date data = new Date();
//        Calendar c = Calendar.getInstance();
//        c.setTime(data);
        //Boolean aa = new Boolean(false);
        ZadanieEntity zadanieEntity = new ZadanieEntity("jzadanie 1", new Date() , new Date(), new Boolean(true));
        zadanieRepository.save(zadanieEntity);
        ZadanieEntity zadanieEntity2 = new ZadanieEntity("ToDo cos tam",new Date(), null, new Boolean(false) );
        zadanieRepository.save(zadanieEntity2);
        ZadanieEntity zadanieEntity3 = new ZadanieEntity("Lalala",new Date(), null , new Boolean(false) );
        zadanieRepository.save(zadanieEntity3);

//        zadaniaRepository.save(user);
//
//        for (int i = 1; i <= 15; i++) {
//            insertTodo(i, false, user);
//            insertTodo(i, true, user);
//        }
    }
}
