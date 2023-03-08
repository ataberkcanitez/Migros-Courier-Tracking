package com.ataberkcanitez.migroscouriertracking.domain.courier.port;

import com.ataberkcanitez.migroscouriertracking.domain.location.model.Location;

public interface CourierLocationPort {
    void track(int courierId, Location location);
}