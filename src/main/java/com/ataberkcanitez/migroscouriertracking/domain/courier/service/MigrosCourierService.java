package com.ataberkcanitez.migroscouriertracking.domain.courier.service;

import com.ataberkcanitez.migroscouriertracking.domain.courier.model.Courier;
import com.ataberkcanitez.migroscouriertracking.domain.courier.port.CourierLocationPort;
import com.ataberkcanitez.migroscouriertracking.infra.adapters.courier.rest.dto.CourierTotalDistanceResponse;
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