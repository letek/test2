package pl.sda.javatarr6.demo.controller;
//package pl.strefakursow.springadvanced.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pl.sda.javatarr6.demo.service.SignUpService;

@Controller
public class WebController {

    @RequestMapping(value="/user_panel", method=RequestMethod.GET)
    public ModelAndView userPanel(ModelAndView mav) {
        mav.setViewName("user_panel");
        return mav;
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

}
