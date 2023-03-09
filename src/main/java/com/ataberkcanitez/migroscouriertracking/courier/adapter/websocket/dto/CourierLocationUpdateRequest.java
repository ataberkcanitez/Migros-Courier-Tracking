package com.ataberkcanitez.migroscouriertracking.courier.adapter.websocket.dto;


import com.ataberkcanitez.migroscouriertracking.location.model.Location;
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