package com.ataberkcanitez.migroscouriertracking.domain.store.service;

import com.ataberkcanitez.migroscouriertracking.domain.common.observer.CourierLocationSubscriber;
import com.ataberkcanitez.migroscouriertracking.domain.location.model.Location;
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

    @PostConstruct
    public void init() {
        courierLocationSubject.attachObserver(this);
    }

    @Override
    public void receiveLocationUpdate(long courierId, Location location) {
        log.info("[StoreEntranceHandler] Courier with id: {} entered the store with location: {}", courierId, location);
        // TODO: get the stores within 100m near to the given location for courier.
    }
}