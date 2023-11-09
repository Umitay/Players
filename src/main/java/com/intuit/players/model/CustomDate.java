package com.intuit.players.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomDate {
    private final String year;
    private final String month;
    private final String day;
}
