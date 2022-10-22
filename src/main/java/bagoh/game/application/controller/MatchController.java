package bagoh.game.application.controller;


import bagoh.game.application.domain.Match;
import bagoh.game.application.domain.Player;
import bagoh.game.application.domain.service.implementation.DefaultMatchService;
import bagoh.game.application.dto.request.RequestMatch;
import bagoh.game.application.util.GenerateTestMatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@RestController
@RequestMapping("/bagoh")
public class MatchController {

//    @Autowired
//    private MatchRepository matchRepository;

    @Autowired
    private DefaultMatchService defaultMatchService;

    @GetMapping(value = "/match/{id}", produces="application/json")
    public ResponseEntity<Match> getById(@Validated @PathVariable(value = "id") Long id) {
        Match matchCreated = new Match(2 , 3);
        return new ResponseEntity<>(matchCreated, HttpStatus.OK);

//    {
//        try {
//            Optional<Match> match = matchRepository.findById(id);
//            return new ResponseEntity<Match>(match, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<Match>(HttpStatus.NOT_FOUND);
//        }
    }

    @GetMapping(value = "/dices", produces="application/json", consumes="application/json")
    public ResponseEntity<List<Player>> getDices(@Validated @RequestParam(name = "id") Long id) {

        defaultMatchService = GenerateTestMatch.gerarRodadaTeste();
        // atualizar gerarDadosJogadores futuramente
        List<Player> players = defaultMatchService.gerarDadosJogadores(false);
        if (Objects.equals(id, defaultMatchService.getMatch().getId())) {
            return new ResponseEntity<>(players, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


//        try {
//            Optional<Match> match = matchRepository.findById(id);
//            defaultMatchService.setMatch(match);
//            // atualizar gerarDadosJogadores futuramente
//            List<Player> players = defaultMatchService.gerarDadosJogadores(false);
//            return new ResponseEntity<>(players, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
    }

    @GetMapping(value = "/battle-dice", produces="application/json", consumes="application/json")
    public ResponseEntity<List<Player>> getBattleDices(@Validated @RequestParam(name = "id") Long id) {

        defaultMatchService = GenerateTestMatch.gerarRodadaTeste();
        // atualizar gerarDadosJogadores futuramente
        List<Player> players = defaultMatchService.gerarDadosJogadores(true);
        if (Objects.equals(id, defaultMatchService.getMatch().getId())) {
            return new ResponseEntity<>(players, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


//        try {
//            Optional<Match> match = matchRepository.findById(id);
//            defaultMatchService.setMatch(match);
//            // atualizar gerarDadosJogadores futuramente
//            List<Player> players = defaultMatchService.gerarDadosJogadores(true);
//            return new ResponseEntity<>(players, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
    }

    @PostMapping(value = "/match", produces="application/json", consumes="application/json")
    public ResponseEntity<Match> post(@Validated @RequestBody RequestMatch match)
    {
         Match matchCreated = new Match(2 , 3);
         return new ResponseEntity<>(matchCreated, HttpStatus.OK);
    }
}
