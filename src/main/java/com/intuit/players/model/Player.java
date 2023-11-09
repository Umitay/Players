package com.intuit.players.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
@Builder
@Document(collection = "player")
public class Player implements Persistable<String> {
    @Id
    private final String id;

    private final String playerID;
    @Indexed
    private final String bbrefID;
    @Indexed
    private final String retroID;

    private final Float height;
    private final Float weight;

    @Indexed
    private final Address birthAddress;
    private final Address deathAddress;

    @Indexed
    private final CustomDate birthDate;
    private final CustomDate deathYear;

    @Indexed
    private final FIO fullName;

    private final Character  bats;

    @Field("throws")
    private final Character  throwss;

    private final Date debut;
    private final  Date finalGame;

    @Override
    @Transient
    public boolean isNew() {
        return true;
    }

}
