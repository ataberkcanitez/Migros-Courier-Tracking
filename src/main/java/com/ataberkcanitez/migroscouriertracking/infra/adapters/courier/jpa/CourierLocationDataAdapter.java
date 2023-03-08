package com.ataberkcanitez.migroscouriertracking.infra.adapters.courier.jpa;

import com.ataberkcanitez.migroscouriertracking.domain.courier.port.CourierLocationPort;
import com.ataberkcanitez.migroscouriertracking.domain.location.model.Location;
import com.ataberkcanitez.migroscouriertracking.infra.adapters.courier.jpa.entity.TravelEntity;
import com.ataberkcanitez.migroscouriertracking.infra.adapters.courier.jpa.repository.CourierTravelJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
public class CourierLocationDataAdapter implements CourierLocationPort {
    private final CourierTravelJpaRepository travelJpaRepository;

    @Override
    public void track(int courierId, Location location) {
        TravelEntity travelEntity = new TravelEntity();
        travelEntity.setCourierId(courierId);
        travelEntity.setLatitude(location.getLat());
        travelEntity.setLongitude(location.getLon());
        travelEntity.setTravelDate(new Timestamp(System.currentTimeMillis()));

        travelJpaRepository.save(travelEntity);
    }
}