package pl.sda.javatarr6.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import pl.sda.javatarr6.demo.component.CustomDaoAuthenticationProvider;
import pl.sda.javatarr6.demo.entity.User;
import pl.sda.javatarr6.demo.entity.ZadanieEntity;
import pl.sda.javatarr6.demo.mapper.ZadaniaMapper;
import pl.sda.javatarr6.demo.repository.UserRepository;
import pl.sda.javatarr6.demo.service.impl.ZadanieService;
import pl.sda.javatarr6.demo.repository.ZadanieRepository;

import javax.servlet.http.HttpServletRequest;
//import org.springframework.security.core.userdetails.UserDetails;


@Controller
public class ZadanieController<ZadanieDto> {
    Long idPrzek;

    //@Autowired
//    private Authentication authentication;


//    @Autowired
//    private UserDetails userDetails;

    @Autowired
    private ZadanieService zadanieService;

    @Autowired
    private ZadanieRepository zadanieRepository;

    @Autowired
    private UserRepository userRepository;

    @Controller
    public class SecurityController {

        @RequestMapping(value = "/username", method = RequestMethod.GET)
        @ResponseBody
        public String currentUserName(Authentication authentication) {
            return authentication.getName();
        }
    }

    @RequestMapping("/delzadanie")
    public String postZadanie(@RequestParam Long id) {
        zadanieService.finishzadanieEntity(id);
        return "redirect:listzadanie";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)

    public String main(Model model) {

        List<pl.sda.javatarr6.demo.dto.ZadanieDto> zadania = zadanieService.getAll();
        long z = 1;
        //List<pl.sda.javatarr6.demo.dto.ZadanieDto> zadania = zadanieService.getZadaniaById(z);
        model.addAttribute("zadania", zadania);
        return "main";
    }

    @RequestMapping(value = "/listzadanie", method = RequestMethod.GET)

    public String listzadanie(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<User> usernameOptional = userRepository.findByUsername(currentPrincipalName);
        //User aaa = new User(usernameOptional.get().getIdUser());
        List<pl.sda.javatarr6.demo.dto.ZadanieDto> zadania = zadanieService.getAllByIdUser(new User(usernameOptional.get().getIdUser()));
        model.addAttribute("zadania", zadania);
        return "listzadanie";
    }

//    @RequestMapping(value = "/zadanieList", method = RequestMethod.GET)
//    @ResponseBody
//    public String zadanieList(Model model) {
//
//        List<pl.sda.javatarr6.demo.dto.ZadanieDto> zadania = zadanieService.getAll();
//        model.addAttribute("zadanieList", zadania);
//        return model.toString();
//    }

    //dodanie zadania
    @RequestMapping(value = "/addzadanie", method = RequestMethod.GET)
    public String createZadanieGet(Model model) {
        //model.addAttribute("zadanie", new pl.sda.javatarr6.demo.dto.ZadanieDto());
        return "addzadanie";
    }

    @RequestMapping(value = "/addzadanie", method = RequestMethod.POST)
    public String createZadanie(@ModelAttribute("zadanie") @Validated pl.sda.javatarr6.demo.dto.ZadanieDto zadanieDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "addzadanie/{id}";
        }
        //  zadanieDto.setOpis();
        zadanieDto.setDataUtworzenia(new SimpleDateFormat(ZadaniaMapper.DATE_FORMAT).format(new Date()));
        zadanieDto.setUkonczone(new Boolean(false));
        zadanieService.save(zadanieDto);
        return "redirect:listzadanie";
    }

    //zmiana opisu
    @RequestMapping(value = "/zmienopis", method = RequestMethod.GET)
    //public String zmienQuiz(@RequestParam Long id, Model model) {
    public String zmienOpis(@RequestParam Long id, Model model) {
        idPrzek = id;//dobrze byłoby może inaczej przekazać tązmienną
        //ZadanieEntity entity1 = zadanieRepository.getById(id); totototot
        ZadanieEntity entity1 = zadanieRepository.getById(id);
        if (entity1.isUkonczone()) {
            System.out.println("Nie można bo zakoczone!");
            return "redirect:listzadanie";
        }
        //model.addAttribute("zadanie", new pl.sda.javatarr6.demo.dto.ZadanieDto());
        return "zmienopis";
    }


    @RequestMapping(value = "/zmienopis", method = RequestMethod.POST)
    public String zmienOpis(@ModelAttribute("zadanie") @Validated pl.sda.javatarr6.demo.dto.ZadanieDto zadanieDto, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "zmienopis";
        }
        zadanieService.zmienpiszadanieEntity(idPrzek, zadanieDto.getOpis());
        return "redirect:listzadanie";
    }

}

