package com.ataberkcanitez.migroscouriertracking.domain.store.model;

import com.ataberkcanitez.migroscouriertracking.domain.location.model.Location;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Store {
    private Long id;
    private String name;
    private Location location;
}
