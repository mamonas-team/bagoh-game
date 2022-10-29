package bagoh.game.application.service;

import bagoh.game.application.domain.service.implementation.DefaultMatchService;
import bagoh.game.application.dto.response.ResponseBattleDices;
import bagoh.game.application.util.GenerateTestMatch;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class ResponseBattleDicesDTOTest {

    @Autowired
    DefaultMatchService defaultMatchService;

    @Test
    void shouldValidatePlayersSize() {
        defaultMatchService  = GenerateTestMatch.gerarRodadaTeste();
        ResponseBattleDices responseBattleDices = defaultMatchService.responseBattleDicesDTO();
        int playersSize = responseBattleDices.getPlayers().size();
        Assertions.assertEquals(3, playersSize);

    }

    @Test
    void shouldValidateBttleDiceSize() {
        defaultMatchService  = GenerateTestMatch.gerarRodadaTeste();
        ResponseBattleDices responseBattleDices = defaultMatchService.responseBattleDicesDTO();
        int battleDicesSize = responseBattleDices.getBattleDices().size();
        Assertions.assertEquals(3, battleDicesSize);

    }

}
