package com.ataberkcanitez.migroscouriertracking.infra.adapters.courier.observer;

import com.ataberkcanitez.migroscouriertracking.domain.common.observer.CourierLocationSubscriber;
import com.ataberkcanitez.migroscouriertracking.domain.location.model.Location;
import com.ataberkcanitez.migroscouriertracking.infra.adapters.courier.websocket.CourierLocationSubject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CourierObserver implements CourierLocationSubject {
    private final List<CourierLocationSubscriber> observers = new ArrayList<>();

    @Override
    public void attachObserver(CourierLocationSubscriber observer) {
        observers.add(observer);
    }

    @Override
    public void detachObserver(CourierLocationSubscriber observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(int courierId, Location location) {
        observers.forEach(observer -> observer.receiveLocationUpdate(courierId, location));
    }
}