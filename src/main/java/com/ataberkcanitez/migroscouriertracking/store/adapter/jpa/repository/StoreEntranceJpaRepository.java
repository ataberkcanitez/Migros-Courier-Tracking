package com.ataberkcanitez.migroscouriertracking.store.adapter.jpa.repository;

import com.ataberkcanitez.migroscouriertracking.store.adapter.jpa.entity.StoreEntranceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreEntranceJpaRepository extends JpaRepository<StoreEntranceEntity, Long> {

}