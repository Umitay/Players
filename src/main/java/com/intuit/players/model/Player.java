package com.intuit.players.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.Date;


@ToString
@EqualsAndHashCode(of = {"playerID","fullName","birthDate"})
@AllArgsConstructor
@Data
@Builder
@Document(collection = "player")
public class Player{
    @Id
    private final String playerID;
    private final String bbrefID;
    private final String retroID;
    private final Float height;
    private final Float weight;
    private final Address birthAddress;
    private final Address deathAddress;
    private final String birthDate;
    private final String deathYear;

    @Indexed
    private final FIO fullName;
    private final Character  bats;

    @Field("throws")
    private final Character  throwss;
    private final LocalDate debut;
    private final  LocalDate finalGame;

}
