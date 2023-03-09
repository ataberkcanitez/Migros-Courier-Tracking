package com.ataberkcanitez.migroscouriertracking.courier.adapter.websocket;

import com.ataberkcanitez.migroscouriertracking.courier.observer.CourierLocationSubscriber;
import com.ataberkcanitez.migroscouriertracking.location.model.Location;

import java.util.List;

public interface CourierLocationSubject {
    void attachObserver(CourierLocationSubscriber observer);
    void detachObserver(CourierLocationSubscriber observer);
    void notifyObservers(long courierId, Location location);
    List<CourierLocationSubscriber> getObservers();
}