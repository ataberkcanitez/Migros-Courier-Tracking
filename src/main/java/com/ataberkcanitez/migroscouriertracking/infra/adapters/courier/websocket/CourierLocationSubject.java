package com.ataberkcanitez.migroscouriertracking.infra.adapters.courier.websocket;

import com.ataberkcanitez.migroscouriertracking.domain.common.observer.CourierLocationSubscriber;
import com.ataberkcanitez.migroscouriertracking.domain.location.model.Location;

public interface CourierLocationSubject {
    void attachObserver(CourierLocationSubscriber observer);
    void detachObserver(CourierLocationSubscriber observer);
    void notifyObservers(int courierId, Location location);
}