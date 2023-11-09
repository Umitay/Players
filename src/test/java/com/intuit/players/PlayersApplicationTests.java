package com.intuit.players;

import com.intuit.players.controller.PlayerController;
import com.intuit.players.service.PlayerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.repository.MongoRepository;


@SpringBootTest
class PlayersApplicationTests {
    @MockBean
    private PlayerController playerController;
    @InjectMocks
    private PlayerService playerService;


    @Mock
    private MongoRepository reactiveRepository;

    @Test
    void contextLoads()  throws Exception {

        //DONE- Added Health Check to properties
        Assertions.assertNotNull(reactiveRepository);
        Assertions.assertNotNull(playerController);
        Assertions.assertNotNull(playerService);

    }


}
