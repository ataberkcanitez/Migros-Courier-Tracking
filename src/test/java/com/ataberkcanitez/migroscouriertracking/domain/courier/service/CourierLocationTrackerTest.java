package com.ataberkcanitez.migroscouriertracking.domain.courier.service;

import com.ataberkcanitez.migroscouriertracking.domain.courier.model.Courier;
import com.ataberkcanitez.migroscouriertracking.domain.courier.port.CourierLocationPort;
import com.ataberkcanitez.migroscouriertracking.domain.courier.port.CourierPort;
import com.ataberkcanitez.migroscouriertracking.domain.location.model.Location;
import com.ataberkcanitez.migroscouriertracking.infra.adapters.courier.websocket.CourierLocationSubject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CourierLocationTrackerTest {

    @Mock
    private CourierLocationPort courierLocationPort;
    @Mock
    private CourierLocationSubject courierLocationSubject;
    @Mock
    private CourierPort courierPort;

    private CourierLocationTracker courierLocationTracker;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        courierLocationTracker = new CourierLocationTracker(courierLocationPort, courierLocationSubject, courierPort);
    }

    @Test
    public void receiveLocationUpdate_shouldTrackLocationAndUpdateCourierTravel() {
        // Given
        long courierId = 1L;
        Location location = new Location(40.7128, -74.0060);
        Courier courier = Courier.builder()
                .id(courierId)
                .name("John Doe")
                .totalTravelDistance(100d)
                .lastLocation(new Location(40.7125, -74.0059))
                .build();

        when(courierLocationPort.getCourierTravelTrack(courierId)).thenReturn(courier);

        // When
        courierLocationTracker.receiveLocationUpdate(courierId, location);

        // Then
        verify(courierLocationPort).track(courierId, location);
        verify(courierLocationPort).getCourierTravelTrack(courierId);
        verify(courierLocationPort).updateCourierTravel(courier);

        assertEquals(courier.getLastLocation(), location);
        assertTrue(courier.getTotalTravelDistance() > 100d);
    }

    @Test
    public void receiveLocationUpdate_shouldInitializeCourierTravelTrack() {
        // Given
        long courierId = 1L;
        Location location = new Location(40.7128, -74.0060);
        Courier courier = Courier.builder()
                .id(courierId)
                .name("John Doe")
                .build();

        when(courierLocationPort.getCourierTravelTrack(courierId)).thenReturn(null);
        when(courierPort.getCourierById(courierId)).thenReturn(courier);

        // When
        courierLocationTracker.receiveLocationUpdate(courierId, location);

        // Then
        verify(courierLocationPort).track(courierId, location);
        verify(courierLocationPort).getCourierTravelTrack(courierId);
        verify(courierLocationPort).updateCourierTravel(courier);


        assertEquals(courier.getLastLocation(), location);
        assertEquals(courier.getTotalTravelDistance(), 0d);
    }
}

