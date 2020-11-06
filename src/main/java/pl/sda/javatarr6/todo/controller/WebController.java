package pl.sda.javatarr6.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.javatarr6.todo.entity.User;
import pl.sda.javatarr6.todo.repository.UserRepository;

import java.util.Optional;

@Controller
public class WebController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value="/user_panel", method=RequestMethod.GET)
    public String user_panel(Model mav) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        Optional<User> usernameOptional = userRepository.findByUsername(currentPrincipalName);

        String userTexta = usernameOptional.get().getText();
        mav.addAttribute("userTexta", userTexta);
        //mav.addAttribute("user_panel");
        return "user_panel";
    }

    @RequestMapping(value="/admin_panel", method=RequestMethod.GET)
    public ModelAndView adminPanel(ModelAndView mav) {
        mav.setViewName("admin_panel");
        return mav;
    }

    @RequestMapping(value="/login", method=RequestMethod.GET)
    public ModelAndView login(ModelAndView mav) {
        mav.setViewName("login");
        return mav;
    }

    @RequestMapping(value="/", method=RequestMethod.GET)
    public ModelAndView log(ModelAndView mav) {
        mav.setViewName("login");
        return mav;
    }
}
