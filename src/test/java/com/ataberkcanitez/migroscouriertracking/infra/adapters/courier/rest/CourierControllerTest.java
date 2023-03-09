package com.ataberkcanitez.migroscouriertracking.infra.adapters.courier.rest;

import com.ataberkcanitez.migroscouriertracking.domain.courier.service.CourierService;
import com.ataberkcanitez.migroscouriertracking.infra.adapters.courier.rest.dto.CourierTotalDistanceResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CourierControllerTest {
    @Mock
    private CourierService courierService;

    private CourierController courierController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        courierController = new CourierController(courierService);
    }

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
