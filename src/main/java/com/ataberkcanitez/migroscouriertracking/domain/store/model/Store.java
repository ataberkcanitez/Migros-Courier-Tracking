package com.ataberkcanitez.migroscouriertracking.domain.store.model;

import com.ataberkcanitez.migroscouriertracking.domain.location.model.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Store {
    private Long id;
    private String name;
    private Location location;
}
