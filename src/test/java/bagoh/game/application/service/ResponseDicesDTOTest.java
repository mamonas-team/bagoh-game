package bagoh.game.application.service;

import bagoh.game.application.domain.service.implementation.DefaultMatchService;
import bagoh.game.application.dto.response.ResponseBattleDices;
import bagoh.game.application.dto.response.ResponseDices;
import bagoh.game.application.util.GenerateTestMatch;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class ResponseDicesDTOTest {

    @Autowired
    DefaultMatchService defaultMatchService;

    @Test
    void shouldValidatePlayersSize() {
        defaultMatchService  = GenerateTestMatch.gerarRodadaTeste();
        ResponseDices responseDices = defaultMatchService.responseDicesDTO();
        int playersSize = responseDices.getPlayers().size();
        Assertions.assertEquals(3, playersSize);

    }

    @Test
    void shouldValidateBttleDiceSize() {
        defaultMatchService  = GenerateTestMatch.gerarRodadaTeste();
        ResponseDices responseDices = defaultMatchService.responseDicesDTO();
        int battleDicesSize = responseDices.getDices().size();
        Assertions.assertEquals(3, battleDicesSize);

    }

}