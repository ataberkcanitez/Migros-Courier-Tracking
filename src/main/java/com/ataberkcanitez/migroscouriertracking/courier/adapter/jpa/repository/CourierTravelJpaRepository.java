package com.ataberkcanitez.migroscouriertracking.courier.adapter.jpa.repository;

import com.ataberkcanitez.migroscouriertracking.courier.adapter.jpa.entity.TravelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourierTravelJpaRepository extends JpaRepository<TravelEntity, Long> {
}
