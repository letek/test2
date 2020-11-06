package pl.sda.javatarr6.todo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pl.sda.javatarr6.todo.entity.User;
import pl.sda.javatarr6.todo.repository.UserRepository;


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
        if (!usernameOptional.isPresent()) {
            throw new UsernameNotFoundException("Nie znaleziono użytkownika: " + username);
        }
        User user = usernameOptional.get();

        return user;
    }
//    public String a(){
//        return this.userRepository.
//    }


}
