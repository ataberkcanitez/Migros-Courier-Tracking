package com.ataberkcanitez.migroscouriertracking.infra.adapters.courier.jpa;

import com.ataberkcanitez.migroscouriertracking.domain.location.model.Location;
import com.ataberkcanitez.migroscouriertracking.infra.adapters.courier.jpa.entity.TravelEntity;
import com.ataberkcanitez.migroscouriertracking.infra.adapters.courier.jpa.repository.CourierTravelJpaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CourierLocationDataAdapterTest {
    @Mock
    private CourierTravelJpaRepository travelJpaRepository;
    @InjectMocks
    private CourierLocationDataAdapter adapter;

    @Test
    public void shouldTrackCourierLocation() {
        // Given
        adapter = new CourierLocationDataAdapter(travelJpaRepository);
        int courierId = 123;
        Location location = new Location(37.7749, -122.4194);

        // When
        adapter.track(courierId, location);

        // Then
        verify(travelJpaRepository).save(any(TravelEntity.class));
    }

}