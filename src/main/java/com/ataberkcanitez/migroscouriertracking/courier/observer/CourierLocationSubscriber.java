package com.ataberkcanitez.migroscouriertracking.courier.observer;

import com.ataberkcanitez.migroscouriertracking.location.model.Location;

public interface CourierLocationSubscriber {
    void receiveLocationUpdate(long courierId, Location location);
}