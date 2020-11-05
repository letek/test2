package pl.sda.javatarr6.demo.service;
//package pl.strefakursow.springadvanced.service;

import org.springframework.stereotype.Component;
import pl.sda.javatarr6.demo.entity.User;

public interface SignUpService {

    User signUpUser(User user);

}
