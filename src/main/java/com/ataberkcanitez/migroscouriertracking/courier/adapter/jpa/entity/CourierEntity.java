package com.ataberkcanitez.migroscouriertracking.courier.adapter.jpa.entity;

import com.ataberkcanitez.migroscouriertracking.courier.model.Courier;
import com.ataberkcanitez.migroscouriertracking.location.model.Location;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "courier")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourierEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    public Courier toModel() {
        return Courier.builder()
                .id(id)
                .name(name)
                .lastLocation(new Location(0.0, 0.0))
                .totalTravelDistance(0.0)
                .build();
    }
}
