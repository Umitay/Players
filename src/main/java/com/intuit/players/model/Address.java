package com.intuit.players.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address {
    private final String country;
    private final String  state;
    private final String  city;
}
