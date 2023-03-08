package com.ataberkcanitez.migroscouriertracking.domain.courier.service;

import com.ataberkcanitez.migroscouriertracking.domain.courier.model.Courier;
import com.ataberkcanitez.migroscouriertracking.domain.courier.port.CourierLocationPort;
import com.ataberkcanitez.migroscouriertracking.domain.location.model.Location;
import com.ataberkcanitez.migroscouriertracking.infra.adapters.courier.rest.dto.CourierTotalDistanceResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MigrosCourierServiceTest {

    @Mock
    private CourierLocationPort courierLocationPort;

    @InjectMocks
    private MigrosCourierService migrosCourierService;

    @Test
    public void testGetTotalTravelDistance() {
        long courierId = 12345;
        double totalDistance = 120.5;

        // given
        Courier courierTravel = new Courier(courierId, "courier1", totalDistance, new Location(50.0, 10.0));

        // when
        when(courierLocationPort.getCourierTravelTrack(courierId)).thenReturn(courierTravel);
        CourierTotalDistanceResponse response = migrosCourierService.getTotalTravelDistance(courierId);


        // then
        Mockito.verify(courierLocationPort).getCourierTravelTrack(courierId);

        assertEquals(response.courierId(), courierId);
        assertEquals(response.totalTravelDistance(), totalDistance, 0.001);
    }

    @Test(expected = RuntimeException.class)
    public void testGetTotalTravelDistanceThrowsException() {
        // given
        long courierId = 12345;

        // when
        when(courierLocationPort.getCourierTravelTrack(courierId)).thenThrow(new RuntimeException());

        // then
        migrosCourierService.getTotalTravelDistance(courierId);
    }
}