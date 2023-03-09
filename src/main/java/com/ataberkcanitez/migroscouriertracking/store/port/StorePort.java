package com.ataberkcanitez.migroscouriertracking.store.port;

import com.ataberkcanitez.migroscouriertracking.location.model.Location;
import com.ataberkcanitez.migroscouriertracking.store.model.Store;
import com.ataberkcanitez.migroscouriertracking.store.adapter.rest.dto.CreateStore;

import java.util.List;

public interface StorePort {
    Store save(CreateStore store);
    List<Store> findAll();
    Store findById(Long id);
    void removeById(Long id);
    Store findNearbyStoresAndNotEnteredRecently(Long courierId, Location location);
    void saveEntrance(long courierId, long storeId);
}
