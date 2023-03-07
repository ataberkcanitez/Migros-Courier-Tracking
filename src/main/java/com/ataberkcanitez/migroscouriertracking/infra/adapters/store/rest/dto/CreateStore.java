package com.ataberkcanitez.migroscouriertracking.infra.adapters.store.rest.dto;

import com.ataberkcanitez.migroscouriertracking.domain.location.model.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateStore {
    private String name;
    private Location location;
}
