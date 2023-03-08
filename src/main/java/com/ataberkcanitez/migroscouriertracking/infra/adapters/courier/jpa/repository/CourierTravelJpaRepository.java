package com.ataberkcanitez.migroscouriertracking.infra.adapters.courier.jpa.repository;

import com.ataberkcanitez.migroscouriertracking.infra.adapters.courier.jpa.entity.TravelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourierTravelJpaRepository extends JpaRepository<TravelEntity, Long> {
}
