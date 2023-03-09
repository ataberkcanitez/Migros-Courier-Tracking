package com.ataberkcanitez.migroscouriertracking.courier.adapter.jpa.repository;

import com.ataberkcanitez.migroscouriertracking.courier.adapter.jpa.entity.CourierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourierJpaRepository extends JpaRepository<CourierEntity, Long> {
}