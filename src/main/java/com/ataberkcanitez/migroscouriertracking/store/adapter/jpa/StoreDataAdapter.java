package com.ataberkcanitez.migroscouriertracking.store.adapter.jpa;

import com.ataberkcanitez.migroscouriertracking.location.model.Location;
import com.ataberkcanitez.migroscouriertracking.store.model.Store;
import com.ataberkcanitez.migroscouriertracking.store.port.StorePort;
import com.ataberkcanitez.migroscouriertracking.store.adapter.jpa.entity.StoreEntity;
import com.ataberkcanitez.migroscouriertracking.store.adapter.jpa.entity.StoreEntranceEntity;
import com.ataberkcanitez.migroscouriertracking.store.adapter.jpa.repository.StoreEntranceJpaRepository;
import com.ataberkcanitez.migroscouriertracking.store.adapter.jpa.repository.StoreJpaRepository;
import com.ataberkcanitez.migroscouriertracking.store.adapter.rest.dto.CreateStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreDataAdapter implements StorePort {
    private final StoreJpaRepository storeJpaRepository;
    private final StoreEntranceJpaRepository storeEntranceJpaRepository;

    @Override
    public Store save(CreateStore store) {
        StoreEntity storeEntity = new StoreEntity();
        storeEntity.setName(store.getName());
        storeEntity.setLatitude(store.getLocation().getLat());
        storeEntity.setLongitude(store.getLocation().getLon());

        return storeJpaRepository.save(storeEntity).toModel();
    }

    @Override
    public List<Store> findAll() {
        return storeJpaRepository.findAll().stream().map(StoreEntity::toModel).toList();
    }

    @Override
    public Store findById(Long id) {
        return storeJpaRepository.findById(id).orElseThrow(() -> new RuntimeException("Store not found")).toModel();
    }

    @Override
    public void removeById(Long id) {
        storeJpaRepository.deleteById(id);
    }

    @Override
    public Store findNearbyStoresAndNotEnteredRecently(Long courierId, Location location) {
        return storeJpaRepository.findNearbyStoresAndNotEnteredRecently(location.getLat(), location.getLon(), courierId)
                .orElseThrow(() -> new RuntimeException("No nearby store found"))
                .toModel();
    }

    @Override
    public void saveEntrance(long courierId, long storeId) {
        StoreEntranceEntity storeEntranceEntity = new StoreEntranceEntity();
        storeEntranceEntity.setStoreId(storeId);
        storeEntranceEntity.setCourierId(courierId);
        storeEntranceEntity.setEntranceDate(new Timestamp(System.currentTimeMillis()));

        storeEntranceJpaRepository.save(storeEntranceEntity);
    }
}
