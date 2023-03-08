package com.ataberkcanitez.migroscouriertracking.domain.store.service;

import com.ataberkcanitez.migroscouriertracking.domain.common.observer.CourierLocationSubscriber;
import com.ataberkcanitez.migroscouriertracking.domain.location.model.Location;
import com.ataberkcanitez.migroscouriertracking.domain.store.model.Store;
import com.ataberkcanitez.migroscouriertracking.domain.store.port.StorePort;
import com.ataberkcanitez.migroscouriertracking.infra.adapters.courier.websocket.CourierLocationSubject;
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
        log.info("[StoreEntranceHandler] Courier with id: {} entered the store with location: {}", courierId, location);
        // TODO: get the stores within 100m near to the given location for courier.
        try {
            Store entranceStore = storePort.findNearbyStoresAndNotEnteredRecently(courierId, location);

            log.info("[StoreEntranceHandler] Courier with id: {} entered the store with id: {}, store name: {}", courierId, entranceStore.getId(), entranceStore.getName());

        } catch (Exception e) {
            log.info("[StoreEntranceHandler] Courier with id: {} did not enter any store", courierId);
        }
    }
}