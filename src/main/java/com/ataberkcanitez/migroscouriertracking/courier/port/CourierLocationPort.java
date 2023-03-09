package com.ataberkcanitez.migroscouriertracking.courier.port;

import com.ataberkcanitez.migroscouriertracking.courier.model.Courier;
import com.ataberkcanitez.migroscouriertracking.location.model.Location;

public interface CourierLocationPort {
    void track(long courierId, Location location);
    Courier getCourierTravelTrack(long courierId);
    void updateCourierTravel(Courier courier);
}