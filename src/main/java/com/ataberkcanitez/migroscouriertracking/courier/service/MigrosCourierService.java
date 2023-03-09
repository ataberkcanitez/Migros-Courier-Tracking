package com.ataberkcanitez.migroscouriertracking.courier.service;

import com.ataberkcanitez.migroscouriertracking.courier.model.Courier;
import com.ataberkcanitez.migroscouriertracking.courier.port.CourierLocationPort;
import com.ataberkcanitez.migroscouriertracking.courier.adapter.rest.dto.CourierTotalDistanceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MigrosCourierService implements CourierService {
    private final CourierLocationPort courierLocationPort;

    @Override
    public CourierTotalDistanceResponse getTotalTravelDistance(long courierId) {
        Courier courierTravel = courierLocationPort.getCourierTravelTrack(courierId);
        if (Objects.isNull(courierTravel)) {
            return new CourierTotalDistanceResponse(courierId, 0d);
        }
        return new CourierTotalDistanceResponse(courierId, courierTravel.getTotalTravelDistance());
    }
}