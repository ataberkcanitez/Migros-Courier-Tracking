package com.ataberkcanitez.migroscouriertracking.domain.courier.service;

import com.ataberkcanitez.migroscouriertracking.domain.common.observer.CourierLocationSubscriber;
import com.ataberkcanitez.migroscouriertracking.domain.courier.model.Courier;
import com.ataberkcanitez.migroscouriertracking.domain.courier.port.CourierLocationPort;
import com.ataberkcanitez.migroscouriertracking.domain.courier.port.CourierPort;
import com.ataberkcanitez.migroscouriertracking.domain.location.model.Location;
import com.ataberkcanitez.migroscouriertracking.infra.adapters.courier.websocket.CourierLocationSubject;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
@AllArgsConstructor
public class CourierLocationTracker implements CourierLocationSubscriber {
    private final CourierLocationPort courierLocationPort;
    private final CourierLocationSubject courierLocationSubject;
    private final CourierPort courierPort;

    @PostConstruct
    public void init() {
        courierLocationSubject.attachObserver(this);
    }

    @Override
    public void receiveLocationUpdate(long courierId, Location location) {
        log.info("[CourierLocationHandler] Courier with id: {} entered the store with location: {}", courierId, location);
        courierLocationPort.track(courierId, location);

        Courier courier = courierLocationPort.getCourierTravelTrack(courierId);
        if (Objects.isNull(courier)) {
            initializeCourierTravelTrack(courierId, location);
            return;
        }
        double distance = courier.getLastLocation().calculateDistance(location);
        courier.setLastLocation(location);
        courier.setTotalTravelDistance(distance);
        courierLocationPort.updateCourierTravel(courier);
    }

    private void initializeCourierTravelTrack(long courierId, Location location) {
        Courier courier = courierPort.getCourierById(courierId);
        courier.setTotalTravelDistance(0d);
        courier.setLastLocation(location);

        courierLocationPort.updateCourierTravel(courier);
        return;
    }
}
