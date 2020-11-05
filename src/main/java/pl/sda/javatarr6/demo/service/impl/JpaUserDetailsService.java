//package pl.strefakursow.springadvanced.service.impl;
package pl.sda.javatarr6.demo.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pl.sda.javatarr6.demo.entity.User;
import pl.sda.javatarr6.demo.repository.UserRepository;

//import pl.strefakursow.springadvanced.entity.User;
//import pl.strefakursow.springadvanced.repository.UserRepository;


@Service
public class JpaUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;


    @Autowired
    public JpaUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> usernameOptional = userRepository.findByUsername(username);
        if(!usernameOptional.isPresent()) {
            throw new UsernameNotFoundException("No user found with username: " + username);
        }
        User user = usernameOptional.get();
//        System.out.println("========================================================================");
//        System.out.println("Dane o uzytkowniku z bazy!");
//        System.out.println("ID: "+user.getIdUser());
//        System.out.println(user.getUsername());
//        System.out.println(user.getPassword());
//        System.out.println("========================================================================");
        return user;
    }



}
