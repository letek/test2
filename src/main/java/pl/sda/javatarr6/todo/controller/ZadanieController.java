package pl.sda.javatarr6.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import pl.sda.javatarr6.todo.entity.User;
import pl.sda.javatarr6.todo.entity.ZadanieEntity;
import pl.sda.javatarr6.todo.mapper.ZadaniaMapper;
import pl.sda.javatarr6.todo.repository.UserRepository;
import pl.sda.javatarr6.todo.service.ZadanieService;
import pl.sda.javatarr6.todo.repository.ZadanieRepository;

@Controller
public class ZadanieController<ZadanieDto> {

    Long idPrzek;

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

        List<pl.sda.javatarr6.todo.dto.ZadanieDto> zadania = zadanieService.getAll();
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

        //   List<pl.sda.javatarr6.todo.dto.ZadanieDto> zadania = zadanieService.getAllByIdUser(new User(usernameOptional.get().getIdUser()));
        List<pl.sda.javatarr6.todo.dto.ZadanieDto> zadania = zadanieService.getAllCompletedByIdUser(false, new User(usernameOptional.get().getIdUser()));
        List<pl.sda.javatarr6.todo.dto.ZadanieDto> zadaniaZak = zadanieService.getAllCompletedByIdUser(true, new User(usernameOptional.get().getIdUser()));
        model.addAttribute("zadania", zadania);
        model.addAttribute("zadaniaZak", zadaniaZak);
        return "listzadanie";
    }
    public String listzadanie_old(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<User> usernameOptional = userRepository.findByUsername(currentPrincipalName);
        //User aaa = new User(usernameOptional.get().getIdUser());

        //   List<pl.sda.javatarr6.todo.dto.ZadanieDto> zadania = zadanieService.getAllByIdUser(new User(usernameOptional.get().getIdUser()));
        List<pl.sda.javatarr6.todo.dto.ZadanieDto> zadania = zadanieService.getAllCompletedByIdUser(false, new User(usernameOptional.get().getIdUser()));
        List<pl.sda.javatarr6.todo.dto.ZadanieDto> zadaniaZak = zadanieService.getAllCompletedByIdUser(true, new User(usernameOptional.get().getIdUser()));
        model.addAttribute("zadania", zadania);
        model.addAttribute("zadaniaZak", zadaniaZak);
        return "listzadanie_old";
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
    public String createZadanie(@ModelAttribute("zadanie") @Validated pl.sda.javatarr6.todo.dto.ZadanieDto zadanieDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "addzadanie/{id}";
        }
        //  zadanieDto.setOpis();
        System.out.println();


        zadanieDto.setDataUtworzenia(new SimpleDateFormat(ZadaniaMapper.DATE_FORMAT).format(new Date()));
        zadanieDto.setUkonczone(new Boolean(false));
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        Optional<User> usernameOptional = userRepository.findByUsername(currentPrincipalName);
        User aaa = new User(usernameOptional.get().getIdUser());

        zadanieDto.setIdUser(aaa);
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
    public String zmienOpis(@ModelAttribute("zadanie") @Validated pl.sda.javatarr6.todo.dto.ZadanieDto zadanieDto, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "zmienopis";
        }

        zadanieService.zmienpiszadanieEntity(idPrzek, zadanieDto.getOpis());
        return "redirect:listzadanie";
    }

}

