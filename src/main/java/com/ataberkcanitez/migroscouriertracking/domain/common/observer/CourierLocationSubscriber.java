package com.ataberkcanitez.migroscouriertracking.domain.common.observer;

import com.ataberkcanitez.migroscouriertracking.domain.location.model.Location;

public interface CourierLocationSubscriber {
    void receiveLocationUpdate(int courierId, Location location);
}