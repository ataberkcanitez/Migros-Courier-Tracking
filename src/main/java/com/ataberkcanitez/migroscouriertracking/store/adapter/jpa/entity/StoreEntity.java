package com.ataberkcanitez.migroscouriertracking.store.adapter.jpa.entity;


import com.ataberkcanitez.migroscouriertracking.location.model.Location;
import com.ataberkcanitez.migroscouriertracking.store.model.Store;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "stores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoreEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    public Store toModel() {
        return Store.builder()
                .id(id)
                .name(name)
                .location(new Location(latitude, longitude))
                .build();
    }
}
