package com.ataberkcanitez.migroscouriertracking.courier.adapter.jpa.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "travel")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "courier_id")
    private long courierId;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "travel_date")
    private Timestamp travelDate;
}
