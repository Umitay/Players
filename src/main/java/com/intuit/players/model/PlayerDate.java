package com.intuit.players.model;

import lombok.Data;

@Data
public class Address {
    private final String country;
    private final String  state;
    private final String  city;
}
