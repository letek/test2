package pl.sda.javatarr6.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pl.sda.javatarr6.todo.entity.User;
import pl.sda.javatarr6.todo.service.SignUpService;



@Controller
public class SignUpController {


    private SignUpService signUpService;

    @Autowired
    public SignUpController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @GetMapping(value="/sign_up")
    public ModelAndView signUp(ModelAndView mav) {
        mav.setViewName("sign_up");
        return mav;
    }

    @PostMapping(value="/sign_up")
    public ModelAndView signUpPost(ModelAndView mav, @RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("text") String text) {
        mav.setViewName("redirect:/login");
        signUpService.signUpUser(new User(username, password,text));
        //System.out.println(username+" | "+password);
        return mav;
    }


}
