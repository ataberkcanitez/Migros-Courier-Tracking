package com.ataberkcanitez.migroscouriertracking.infra.adapters.courier.jpa;

import com.ataberkcanitez.migroscouriertracking.domain.courier.model.Courier;
import com.ataberkcanitez.migroscouriertracking.domain.courier.port.CourierLocationPort;
import com.ataberkcanitez.migroscouriertracking.domain.location.model.Location;
import com.ataberkcanitez.migroscouriertracking.infra.adapters.courier.jpa.entity.TravelEntity;
import com.ataberkcanitez.migroscouriertracking.infra.adapters.courier.jpa.repository.CourierTravelJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.HashMap;

@Component
@RequiredArgsConstructor
public class CourierLocationDataAdapter implements CourierLocationPort {
    private final CourierTravelJpaRepository travelJpaRepository;
    private HashMap<Long, Courier> courierTravelMap = new HashMap<>();

    /*
        Responsible for writing every update to database.
     */
    @Override
    public void track(long courierId, Location location) {
        TravelEntity travelEntity = new TravelEntity();
        travelEntity.setCourierId(courierId);
        travelEntity.setLatitude(location.getLat());
        travelEntity.setLongitude(location.getLon());
        travelEntity.setTravelDate(new Timestamp(System.currentTimeMillis()));

        travelJpaRepository.save(travelEntity);
    }


    /*
        Responsible for reading the courier travel data from in memory cache.
     */
    @Override
    public Courier getCourierTravelTrack(long courierId) {
        return courierTravelMap.get(courierId);
    }

    /*
        Responsible for updating the courier travel data in memory cache.
     */
    @Override
    public void updateCourierTravel(Courier courier) {
        courierTravelMap.put(courier.getId(), courier);
    }
}