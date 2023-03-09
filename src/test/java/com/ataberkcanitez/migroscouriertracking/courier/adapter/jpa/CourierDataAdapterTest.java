package com.ataberkcanitez.migroscouriertracking.courier.adapter.jpa;

import com.ataberkcanitez.migroscouriertracking.courier.model.Courier;
import com.ataberkcanitez.migroscouriertracking.location.model.Location;
import com.ataberkcanitez.migroscouriertracking.courier.adapter.jpa.entity.CourierEntity;
import com.ataberkcanitez.migroscouriertracking.courier.adapter.jpa.repository.CourierJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class CourierDataAdapterTest {

    @Mock
    private CourierJpaRepository courierJpaRepository;

    private CourierDataAdapter courierDataAdapter;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        courierDataAdapter = new CourierDataAdapter(courierJpaRepository);
    }

    @Test
    public void testGetCourierById() {
        // given
        long courierId = 1L;
        String courierName = "Courier1";
        CourierEntity courierEntity = new CourierEntity(courierId, courierName);
        when(courierJpaRepository.findById(courierId)).thenReturn(Optional.of(courierEntity));

        // when
        Courier courier = courierDataAdapter.getCourierById(courierId);

        // then
        assertNotNull(courier);
        assertEquals(courier.getId(), courierId);
        assertEquals(courier.getName(), courierName);
        assertEquals(courier.getTotalTravelDistance(), 0.0, 0.0);
        assertEquals(courier.getLastLocation().getLat(), new Location(0.0, 0.0).getLat());
        assertEquals(courier.getLastLocation().getLon(), new Location(0.0, 0.0).getLon());
    }
}
