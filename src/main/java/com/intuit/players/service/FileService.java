package com.intuit.players.service;

import com.intuit.players.model.Address;
import com.intuit.players.model.CustomDate;
import com.intuit.players.model.FIO;
import com.intuit.players.model.Player;

import com.intuit.players.repository.IMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

@Service
public class FileService implements CompletionHandler<Integer, AsynchronousFileChannel> {

    private int pos = 0;
    private AsynchronousFileChannel channel =  null;
    private ByteBuffer buffer = null;
    private final IMongoRepository playerRepository;

    @Autowired
    public FileService(IMongoRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public void completed(Integer result, AsynchronousFileChannel attachment) {

        if (result != -1) {
            pos += result;  // don't read the same text again.
            int i = 0;

            String strLines = new String(buffer.array());
            if(!strLines.contains("playerID")) {
                String[] arrLines = strLines.split("\\n");

                for (String strLine : arrLines) {

                    if (i > 10) break;

                    String[] arrLine = strLine.split(",");

                    if (arrLine.length-1 >22) {

                        Player player = Player.builder()
                                .playerID(arrLine[0])
                                .birthDate(CustomDate.builder().day(arrLine[3]).month(arrLine[1]).year(arrLine[1]).build())
                                .birthAddress(Address.builder().country(arrLine[4]).state(arrLine[5]).city(arrLine[6]).build())
                                .fullName(FIO.builder().name(arrLine[13]).nameLast(arrLine[14]).nameGiven(arrLine[15]).build())
                                .retroID(arrLine[22])
                                .bbrefID(arrLine[23]).build();
                         playerRepository.insert(player);
                        System.out.println(player.toString());
                        i++;
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
        String filePath = "/Users/ut/Desktop/projects/Players/player.csv";
        Path path = Paths.get(filePath);
        channel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
        buffer = ByteBuffer.allocate(1000);
        channel.read(buffer, pos , channel, this );
    }
}

