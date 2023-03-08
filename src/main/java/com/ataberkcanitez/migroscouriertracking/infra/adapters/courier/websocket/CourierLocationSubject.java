package com.ataberkcanitez.migroscouriertracking.infra.adapters.courier.websocket;

import com.ataberkcanitez.migroscouriertracking.domain.common.observer.CourierLocationSubscriber;
import com.ataberkcanitez.migroscouriertracking.domain.location.model.Location;

import java.util.List;

public interface CourierLocationSubject {
    void attachObserver(CourierLocationSubscriber observer);
    void detachObserver(CourierLocationSubscriber observer);
    void notifyObservers(long courierId, Location location);
    List<CourierLocationSubscriber> getObservers();
}