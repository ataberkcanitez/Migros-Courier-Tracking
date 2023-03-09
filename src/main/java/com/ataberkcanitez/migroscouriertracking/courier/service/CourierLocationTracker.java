package com.ataberkcanitez.migroscouriertracking.courier.service;

import com.ataberkcanitez.migroscouriertracking.courier.observer.CourierLocationSubscriber;
import com.ataberkcanitez.migroscouriertracking.courier.model.Courier;
import com.ataberkcanitez.migroscouriertracking.courier.port.CourierLocationPort;
import com.ataberkcanitez.migroscouriertracking.courier.port.CourierPort;
import com.ataberkcanitez.migroscouriertracking.location.model.Location;
import com.ataberkcanitez.migroscouriertracking.courier.adapter.websocket.CourierLocationSubject;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Objects;

@Service
@Slf4j
@AllArgsConstructor
public class CourierLocationTracker implements CourierLocationSubscriber {
    private final CourierLocationPort courierLocationPort;
    private final CourierLocationSubject courierLocationSubject;
    private final CourierPort courierPort;

    private final DecimalFormat df = new DecimalFormat("0.00");

    @PostConstruct
    public void init() {
        courierLocationSubject.attachObserver(this);
    }

    @Override
    public void receiveLocationUpdate(long courierId, Location location) {
        courierLocationPort.track(courierId, location);

        Courier courier = courierLocationPort.getCourierTravelTrack(courierId);
        if (Objects.isNull(courier)) {
            initializeCourierTravelTrack(courierId, location);
            return;
        }
        double distance = courier.getLastLocation().calculateDistance(location);
        double formattedDistance = Double.parseDouble(df.format(distance));
        courier.setLastLocation(location);
        courier.setTotalTravelDistance(courier.getTotalTravelDistance() + formattedDistance);
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
