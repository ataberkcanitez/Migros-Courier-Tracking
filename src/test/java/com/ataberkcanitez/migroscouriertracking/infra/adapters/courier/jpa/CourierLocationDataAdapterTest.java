package com.ataberkcanitez.migroscouriertracking.infra.adapters.courier.jpa;

import com.ataberkcanitez.migroscouriertracking.domain.courier.model.Courier;
import com.ataberkcanitez.migroscouriertracking.domain.location.model.Location;
import com.ataberkcanitez.migroscouriertracking.infra.adapters.courier.jpa.entity.TravelEntity;
import com.ataberkcanitez.migroscouriertracking.infra.adapters.courier.jpa.repository.CourierTravelJpaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
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
        long courierId = 123;
        Location location = new Location(37.7749, -122.4194);

        // When
        adapter.track(courierId, location);

        // Then
        verify(travelJpaRepository).save(any(TravelEntity.class));
    }

    @Test
    public void testGetCourierTravelTrack() {
        // given
        Courier courier = Courier.builder()
                .id(1)
                .name("Courier 1")
                .totalTravelDistance(10.0)
                .lastLocation(new Location(40.7128, -74.0060))
                .build();

        // when
        adapter.updateCourierTravel(courier);

        //then
        Courier resultCourier = adapter.getCourierTravelTrack(1L);
        assertEquals(courier, resultCourier);
    }

    @Test
    public void testGetCourierTravelTrackNotFound() {
        assertNull(adapter.getCourierTravelTrack(2L));
    }

    @Test
    public void testUpdateCourierTravel() {
        // given
        Courier updatedCourier = Courier.builder()
                .id(1)
                .name("Courier 1")
                .totalTravelDistance(20.0)
                .lastLocation(new Location(37.7749, -122.4194))
                .build();

        // when
        adapter.updateCourierTravel(updatedCourier);

        // then
        Courier resultCourier = adapter.getCourierTravelTrack(1L);
        assertEquals(updatedCourier, resultCourier);
    }
}