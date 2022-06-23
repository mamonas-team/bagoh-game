package application.game.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MatchController {

    @GetMapping("/partida")
    public String getPartida(){
        return "Você está na Partida 01";
    }

}
