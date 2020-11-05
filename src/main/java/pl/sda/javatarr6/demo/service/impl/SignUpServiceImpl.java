package pl.sda.javatarr6.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import pl.sda.javatarr6.demo.entity.User;
import pl.sda.javatarr6.demo.repository.UserRepository;
import pl.sda.javatarr6.demo.service.SignUpService;
//import pl.sda.javatarr6.demo.component.CustomDaoAuthenticationProvider;

import java.util.Optional;

@Service
public class SignUpServiceImpl implements SignUpService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public SignUpServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User signUpUser(User user) {
        Assert.isNull(user.getIdUser(), "Can't sign up given user, it already has set id. User: " + user.toString());

        Optional<User> usernameOptional = userRepository.findByUsername(user.getUsername());

        if(usernameOptional.isPresent()) {
            //System.out.println("Juz jest taki użytkownik: " + user.getUsername());
            throw new UsernameNotFoundException("Juz jest taki użytkownik: " + user.getUsername());
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        return savedUser;
    }

}
