package com.ataberkcanitez.migroscouriertracking.domain.courier.port;

import com.ataberkcanitez.migroscouriertracking.domain.courier.model.Courier;
import com.ataberkcanitez.migroscouriertracking.domain.location.model.Location;

public interface CourierLocationPort {
    void track(long courierId, Location location);
    Courier getCourierTravelTrack(long courierId);
    void updateCourierTravel(Courier courier);
}