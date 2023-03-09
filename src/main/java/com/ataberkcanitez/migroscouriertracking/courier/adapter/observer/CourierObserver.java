package com.ataberkcanitez.migroscouriertracking.courier.adapter.observer;

import com.ataberkcanitez.migroscouriertracking.courier.observer.CourierLocationSubscriber;
import com.ataberkcanitez.migroscouriertracking.location.model.Location;
import com.ataberkcanitez.migroscouriertracking.courier.adapter.websocket.CourierLocationSubject;
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
    public void notifyObservers(long courierId, Location location) {
        observers.forEach(observer -> observer.receiveLocationUpdate(courierId, location));
    }

    @Override
    public List<CourierLocationSubscriber> getObservers() {
        return observers;
    }
}