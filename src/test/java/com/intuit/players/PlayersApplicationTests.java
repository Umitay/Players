package com.intuit.players;

import com.intuit.players.controller.PlayerController;
import com.intuit.players.service.PlayerService;
import com.intuit.players.repository.IReactiveMongoRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.actuate.observability.AutoConfigureObservability;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;



@SpringBootTest
//@AutoConfigureObservability
class PlayersApplicationTests {
    @MockBean
    private PlayerController playerController;
    @MockBean
    private PlayerService playerService;

    @MockBean
    private IReactiveMongoRepository reactiveRepository;

    @Test
    void contextLoads()  throws Exception {
        Assertions.assertNotNull(reactiveRepository);
        Assertions.assertNotNull(playerController);
        Assertions.assertNotNull(playerService);
    }


}
