package com.intuit.players.service;

import com.intuit.players.model.Address;
import com.intuit.players.model.FIO;
import com.intuit.players.model.Player;

import com.intuit.players.repository.IReactiveMongoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
@Transactional
public class FileService implements CompletionHandler<Integer, AsynchronousFileChannel> {

    private int pos = 0;
    private AsynchronousFileChannel channel =  null;
    private ByteBuffer buffer = null;
    private final IReactiveMongoRepository playerRepository;

    @Autowired
    public FileService(IReactiveMongoRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public void completed(Integer result, AsynchronousFileChannel attachment) {

        if (result != -1) {
            pos += result;  // don't read the same text again.

            String strLines = new String(buffer.array());
            //TODO Reading CSV input file by field name instead of position
            if(!strLines.contains("playerID")) {
                String[] arrLines = strLines.split("\\n");

                for (String strLine : arrLines) {

                    String[] arrLine = strLine.split(",");

                    if (arrLine.length-1 >22) {

                            Player player = Player.builder()
                                    .playerID(arrLine[0])
                                    .birthDate( arrLine[1]+"-"+arrLine[2]+"-"+arrLine[3])
                                    .birthAddress(Address.builder().country(arrLine[4]).state(arrLine[5]).city(arrLine[6]).build())
                                    .fullName(FIO.builder().name(arrLine[13]).nameLast(arrLine[14]).nameGiven(arrLine[15]).build())
                                    .retroID(arrLine[22])
                                    .bbrefID(arrLine[23]).build();
                            playerRepository.save(player)
                                    .subscribe(savedEntity -> log.info("Entity has been saved: {}", savedEntity));


                    }


                }

            }
        buffer.clear();  // reset the buffer so you can read more.
        }
        // initiate another asynchronous read, with this.
        attachment.read(buffer, pos , attachment, this );
}
    public void failed(Throwable exc,
        AsynchronousFileChannel attachment) {
        System.err.println ("Error!");
        exc.printStackTrace();
        }

    public  void readFile()  throws IOException, InterruptedException, ExecutionException {
        //TODO change the hard coded path
        String filePath = "/Users/ut/Desktop/projects/Players/player.csv";
        Path path = Paths.get(filePath);
        channel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
        buffer = ByteBuffer.allocate(1000);
        channel.read(buffer, pos , channel, this );
    }
}

