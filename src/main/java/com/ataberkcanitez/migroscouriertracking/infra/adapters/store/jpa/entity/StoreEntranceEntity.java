package com.ataberkcanitez.migroscouriertracking.infra.adapters.store.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
@Table(name = "entrances")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreEntranceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "store_id")
    private Long storeId;

    @Column(name = "courier_id")
    private Long courierId;

    @Column(name = "entrance_date")
    private LocalDate entranceDate;
}
