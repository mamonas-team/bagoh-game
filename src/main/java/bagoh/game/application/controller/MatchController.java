package bagoh.game.application.controller;


import bagoh.game.application.domain.Bid;
import bagoh.game.application.domain.Match;
import bagoh.game.application.domain.DiceValues;
import bagoh.game.application.domain.service.implementation.DefaultMatchService;
import bagoh.game.application.dto.request.RequestBid;
import bagoh.game.application.dto.request.RequestMatch;
import bagoh.game.application.dto.response.ResponseBattleDices;
import bagoh.game.application.dto.response.ResponseBidValidator;
import bagoh.game.application.dto.response.ResponseDices;
import bagoh.game.application.dto.response.ResponseSaveBid;
import bagoh.game.application.util.GenerateTestMatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.Objects;
import java.util.Optional;


@RestController
@RequestMapping("/bagoh")
public class MatchController {

//    @Autowired
//    private MatchRepository matchRepository;

    @Autowired
    private DefaultMatchService defaultMatchService;

    @GetMapping(value = "/match/{idMatch}", produces="application/json")
    public ResponseEntity<Match> getById(@Validated @PathVariable(value = "idMatch") Long idMatch) {
        Match matchCreated = new Match(2 , 3);
        return new ResponseEntity<>(matchCreated, HttpStatus.OK);

//    {
//        try {
//            Optional<Match> match = matchRepository.findById(idMatch);
//            return new ResponseEntity<Match>(match, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<Match>(HttpStatus.NOT_FOUND);
//        }
    }

    @GetMapping(value = "/dices", produces="application/json", consumes="application/json")
    public ResponseEntity<ResponseDices> getDices(@Validated @RequestParam(name = "idMatch") Long idMatch) {

        defaultMatchService = GenerateTestMatch.gerarRodadaTeste();
        // atualizar gerarDadosJogadores futuramente
        defaultMatchService.gerarDadosJogadores(false);
        ResponseDices responseDices = defaultMatchService.responseDicesDTO();

        if (Objects.equals(idMatch, defaultMatchService.getMatch().getId())) {
            return new ResponseEntity<>(responseDices, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


//        try {
//            Optional<Match> match = matchRepository.findById(idMatch);
//            defaultMatchService.setMatch(match);
//            // atualizar gerarDadosJogadores futuramente
//            List<Player> players = defaultMatchService.gerarDadosJogadores(false);
//            return new ResponseEntity<>(players, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
    }

    @GetMapping(value = "/battle-dice", produces="application/json", consumes="application/json")
    public ResponseEntity<ResponseBattleDices> getBattleDices(@Validated @RequestParam(name = "idMatch") Long idMatch) {

        defaultMatchService = GenerateTestMatch.gerarRodadaTeste();
        // atualizar gerarDadosJogadores futuramente
        defaultMatchService.gerarDadosJogadores(true);
        ResponseBattleDices responseBattleDices = defaultMatchService.responseBattleDicesDTO();

        if (Objects.equals(idMatch, defaultMatchService.getMatch().getId())) {
            return new ResponseEntity<>(responseBattleDices, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

//        try {
//            Optional<Match> match = matchRepository.findById(idMatch);
//            defaultMatchService.setMatch(match);
//            // atualizar gerarDadosJogadores futuramente
//            defaultMatchService.gerarDadosJogadores(true);
//            ResponseBattleDices responseBattleDices = defaultMatchService.responseBattleDicesDTO();
//
//            return new ResponseEntity<>(responseBattleDices, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }

    }

    @PostMapping(value = "/save-bid", produces="application/json", consumes="application/json")
    public ResponseEntity<ResponseSaveBid> saveBid(@Validated @RequestBody RequestBid bidReceived, @Validated @RequestParam(name = "idMatch") Long idMatch) {

        defaultMatchService = GenerateTestMatch.gerarRodadaTeste();
        int bidTypeNum = bidReceived.getBid()[1];
        DiceValues bidType = null;
        int bidQuantity = bidReceived.getBid()[0];
        
        switch (bidTypeNum) {

            case 1:
                bidType = DiceValues.BAGO;
                break;

            case 2:
                bidType = DiceValues.DUQUE;
                break;

            case 3:
                bidType = DiceValues.TERNO;
                break;

            case 4:
                bidType = DiceValues.QUADRA;
                break;

            case 5:
                bidType = DiceValues.QUINA;
                break;

            case 6:
                bidType = DiceValues.SENA;
                break;
                
            default:
                break;
        }
        
        Bid bid = new Bid(bidType, bidQuantity, bidReceived.getIdPlayer());
        defaultMatchService.saveBid(bid);
        ResponseSaveBid responseSaveBid = defaultMatchService.responseSaveBidDTO(bid);

        if (Objects.equals(idMatch, defaultMatchService.getMatch().getId())) {
            return new ResponseEntity<>(responseSaveBid, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


//        try {
//            Optional<Match> match = matchRepository.findById(idMatch);
//            defaultMatchService.setMatch(match);
//            defaultMatchService.saveBid(bid);
//            ResponseSaveBid responseSaveBid = defaultMatchService.responseSaveBidDTO(bid);
//            return new ResponseEntity<>(responseSaveBid, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
    }

    @GetMapping(value = "/bid-validator", produces="application/json", consumes="application/json")
    public ResponseEntity<ResponseBidValidator> bidValidation(@Validated @RequestParam(name = "idMatch") Long idMatch, @Validated @RequestBody Long idPlayer) {

        defaultMatchService = GenerateTestMatch.gerarRodadaTeste();
        defaultMatchService.validateIfBidIsTrue();
        ResponseBidValidator responseBidValidator = defaultMatchService.responseBidValidatorDTO(idPlayer);

        if (Objects.equals(idMatch, defaultMatchService.getMatch().getId())) {

            return new ResponseEntity<>(responseBidValidator, HttpStatus.OK);
        } else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


//        try {
//            Optional<Match> match = matchRepository.findById(idMatch);
//            defaultMatchService.setMatch(match);
//            defaultMatchService.validateIfBidIsTrue();
//            ResponseBidValidator responseBidValidator = defaultMatchService.responseBidValidatorDTO(idPlayer);
//
//            return new ResponseEntity<>(responseBidValidator, HttpStatus.OK);
//        } catch (Exception e) {
//
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
