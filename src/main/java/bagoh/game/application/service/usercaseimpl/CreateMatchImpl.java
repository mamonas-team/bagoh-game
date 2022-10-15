package bagoh.game.application.service.usercaseimpl;


import bagoh.game.application.entity.Match;
import bagoh.game.application.repository.MatchRepository;
import bagoh.game.application.service.usercase.CreateMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateMatchImpl implements CreateMatchService {


    @Autowired
    MatchRepository matchRepository;

    @Override
    public Match createMatch(long numberOfPlayers, long numberOfDices) {
        Match match = new Match(2,3);
        return matchRepository.save(match);
    }
}
