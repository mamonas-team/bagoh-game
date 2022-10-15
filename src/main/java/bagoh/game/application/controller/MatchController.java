package bagoh.game.application.controller;


import bagoh.game.application.domain.Match;
import bagoh.game.application.dto.request.RequestMatchDto;
//import bagoh.game.application.entity.Match;
//import bagoh.game.application.repository.MatchRepository;
//import bagoh.game.application.service.usercase.CreateMatchService;
import bagoh.game.application.domain.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
public class MatchController {

//    @Autowired
//    private MatchRepository matchRepository;

    @Autowired
    private MatchService matchService;


    @GetMapping(value = "/match/{id}", produces="application/json")
    public ResponseEntity<Match> GetById(@PathVariable(value = "id") long id) {
        Match matchCreated = new Match(2 , 3);
        return new ResponseEntity<Match>(matchCreated, HttpStatus.OK);

//    {
//        try {
//            Optional<Match> match = matchRepository.findById(id);
//            return new ResponseEntity<Match>(match.get(), HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<Match>(HttpStatus.NOT_FOUND);
//        }
    }

    @PostMapping(value = "/match", produces="application/json", consumes="application/json")
    public ResponseEntity<Match> Post(@Validated @RequestBody RequestMatchDto match)
    {
         Match matchCreated = new Match(2 , 3);
         return new ResponseEntity<Match>(matchCreated, HttpStatus.OK);
    }
}
