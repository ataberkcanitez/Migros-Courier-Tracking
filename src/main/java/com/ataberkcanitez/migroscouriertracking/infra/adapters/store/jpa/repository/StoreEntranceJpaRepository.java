package com.ataberkcanitez.migroscouriertracking.infra.adapters.store.jpa.repository;

import com.ataberkcanitez.migroscouriertracking.infra.adapters.store.jpa.entity.StoreEntranceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreEntranceJpaRepository extends JpaRepository<StoreEntranceEntity, Long> {

}