package com.intuit.players.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FIO {
    private final String name;
    private final String  nameGiven;
    private final String  nameLast;
}
