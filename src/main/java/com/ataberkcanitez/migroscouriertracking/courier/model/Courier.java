package com.ataberkcanitez.migroscouriertracking.courier.model;

import com.ataberkcanitez.migroscouriertracking.location.model.Location;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Courier {
    private long id;
    private String name;
    private double totalTravelDistance;
    private Location lastLocation;
}