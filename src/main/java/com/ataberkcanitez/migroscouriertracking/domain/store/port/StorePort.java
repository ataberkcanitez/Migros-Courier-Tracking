package com.ataberkcanitez.migroscouriertracking.domain.store.port;

import com.ataberkcanitez.migroscouriertracking.domain.location.model.Location;
import com.ataberkcanitez.migroscouriertracking.domain.store.model.Store;
import com.ataberkcanitez.migroscouriertracking.infra.adapters.store.rest.dto.CreateStore;

import java.util.List;

public interface StorePort {
    Store save(CreateStore store);
    List<Store> findAll();
    Store findById(Long id);
    void removeById(Long id);
    Store findNearbyStoresAndNotEnteredRecently(Long courierId, Location location);
}
