package bagoh.game.application.service;

import bagoh.game.application.service.implementation.DefaultMatchService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DefaultMatchServiceTest {

    @Test
    void shouldAddTwoNumbers(){
        DefaultMatchService service = new DefaultMatchService();
        int resultadoDoMetodo = service.soma(2,3);
        int resultadoEsperado = 6;

        Assertions.assertTrue(resultadoDoMetodo == resultadoEsperado);
    }

}
