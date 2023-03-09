package com.ataberkcanitez.migroscouriertracking.courier.adapter.jpa;

import com.ataberkcanitez.migroscouriertracking.courier.model.Courier;
import com.ataberkcanitez.migroscouriertracking.courier.port.CourierLocationPort;
import com.ataberkcanitez.migroscouriertracking.location.model.Location;
import com.ataberkcanitez.migroscouriertracking.courier.adapter.jpa.entity.TravelEntity;
import com.ataberkcanitez.migroscouriertracking.courier.adapter.jpa.repository.CourierTravelJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class CourierLocationDataAdapter implements CourierLocationPort {
    private final CourierTravelJpaRepository travelJpaRepository;
    private final ConcurrentHashMap<Long, Courier> courierTravelMap = new ConcurrentHashMap<>();

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