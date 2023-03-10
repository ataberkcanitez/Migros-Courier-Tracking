package com.ataberkcanitez.migroscouriertracking.store.adapter.rest.dto;

import com.ataberkcanitez.migroscouriertracking.location.model.Location;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateStore {
    @NotBlank
    private String name;
    @NotNull
    private Location location;
}
