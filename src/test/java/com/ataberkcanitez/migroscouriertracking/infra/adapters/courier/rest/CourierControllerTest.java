package com.ataberkcanitez.migroscouriertracking.infra.adapters.courier.rest;

import com.ataberkcanitez.migroscouriertracking.domain.courier.service.CourierService;
import com.ataberkcanitez.migroscouriertracking.infra.adapters.courier.rest.dto.CourierTotalDistanceResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CourierControllerTest {

    @Mock
    private CourierService courierService;

    @InjectMocks
    private CourierController courierController;

    @Test
    public void testGetTotalTravelDistance() {
        long courierId = 1L;
        CourierTotalDistanceResponse expectedResponse = new CourierTotalDistanceResponse(courierId, 100.0);

        when(courierService.getTotalTravelDistance(courierId)).thenReturn(expectedResponse);

        ResponseEntity<CourierTotalDistanceResponse> responseEntity = courierController.getTotalTravelDistance(courierId);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertEquals(responseEntity.getBody().courierId(), courierId);

        verify(courierService, times(1)).getTotalTravelDistance(courierId);
    }
}
