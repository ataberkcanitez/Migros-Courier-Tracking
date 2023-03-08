package com.ataberkcanitez.migroscouriertracking.infra.adapters.courier.jpa.repository;

import com.ataberkcanitez.migroscouriertracking.infra.adapters.courier.jpa.entity.CourierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourierJpaRepository extends JpaRepository<CourierEntity, Long> {
}