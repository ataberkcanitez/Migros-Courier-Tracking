package com.ataberkcanitez.migroscouriertracking.domain.common.observer;

import com.ataberkcanitez.migroscouriertracking.domain.location.model.Location;

public interface CourierLocationSubscriber {
    void receiveLocationUpdate(long courierId, Location location);
}