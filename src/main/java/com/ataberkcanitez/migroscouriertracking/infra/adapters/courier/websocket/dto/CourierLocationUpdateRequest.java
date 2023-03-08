package com.ataberkcanitez.migroscouriertracking.infra.adapters.courier.websocket.dto;


import com.ataberkcanitez.migroscouriertracking.domain.location.model.Location;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CourierLocationUpdateRequest {
    private long courierId;
    private Location location;
}