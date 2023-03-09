package com.ataberkcanitez.migroscouriertracking.store.service;

import com.ataberkcanitez.migroscouriertracking.courier.observer.CourierLocationSubscriber;
import com.ataberkcanitez.migroscouriertracking.location.model.Location;
import com.ataberkcanitez.migroscouriertracking.store.model.Store;
import com.ataberkcanitez.migroscouriertracking.store.port.StorePort;
import com.ataberkcanitez.migroscouriertracking.courier.adapter.websocket.CourierLocationSubject;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class StoreEntranceHandler implements CourierLocationSubscriber {
    private final CourierLocationSubject courierLocationSubject;
    private final StorePort storePort;

    @PostConstruct
    public void init() {
        courierLocationSubject.attachObserver(this);
    }

    @Override
    public void receiveLocationUpdate(long courierId, Location location) {
        try {
            Store entranceStore = storePort.findNearbyStoresAndNotEnteredRecently(courierId, location);
            log.info("[StoreEntranceHandler] entranceStore: {}", entranceStore.toString());

            log.info("[StoreEntranceHandler] Courier with id: {} entered the store with id: {}, store name: {}", courierId, entranceStore.getId(), entranceStore.getName());
            storePort.saveEntrance(courierId, entranceStore.getId());
        } catch (Exception e) {
            log.info("[StoreEntranceHandler] Courier with id: {} did not enter any store", courierId);
        }
    }
}