package com.ataberkcanitez.migroscouriertracking.infra.adapters.store.jpa.repository;

import com.ataberkcanitez.migroscouriertracking.infra.adapters.store.jpa.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreJpaRepository extends JpaRepository<StoreEntity, Long> {

}
