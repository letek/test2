package pl.sda.javatarr6.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.sda.javatarr6.demo.service.ZadanieService;

@RestController
public class ZadanieRESTController {
    //public class QuizRestController {

    @Autowired
    private ZadanieService zadanieService;

    @DeleteMapping("/api/zadanie/{id}")
    public Long deleteZadanie(@PathVariable Long id) {
        // TODO: usunięcie elementu z bazy
        //ZadanieService zadanieService1 = new ZadanieService();
       zadanieService.finishzadanieEntity(id);
        return id;
    }



}
