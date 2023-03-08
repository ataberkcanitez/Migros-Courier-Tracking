package com.ataberkcanitez.migroscouriertracking.domain.courier.model;

import com.ataberkcanitez.migroscouriertracking.domain.location.model.Location;
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