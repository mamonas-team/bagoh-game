//package bagoh.game.application.controller;
//
//
//import bagoh.game.application.dto.requestDto.RequestMatchDto;
//import bagoh.game.application.entity.Match;
//import bagoh.game.application.repository.MatchRepository;
//import bagoh.game.application.service.usercase.CreateMatchService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Optional;
//
//
//@RestController
//public class MatchController {
//
//    @Autowired
//    private MatchRepository matchRepository;
//
//    @Autowired
//    private CreateMatchService createMatchService;
//
//
//    @GetMapping(value = "/match/{id}", produces="application/json")
//    public ResponseEntity<Match> GetById(@PathVariable(value = "id") long id)
//    {
//        try {
//            Optional<Match> match = matchRepository.findById(id);
//            return new ResponseEntity<Match>(match.get(), HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<Match>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @PostMapping(value = "/match", produces="application/json", consumes="application/json")
//    public ResponseEntity<Match> Post(@Validated @RequestBody RequestMatchDto match)
//    {
//         Match matchCreated = createMatchService.createMatch(match.getNumberOfPlayers(), match.getNumberOfDices());
//         return new ResponseEntity<Match>(matchCreated, HttpStatus.OK);
//    }
//}
