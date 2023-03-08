package com.ataberkcanitez.migroscouriertracking.infra.adapters.courier.jpa;

import com.ataberkcanitez.migroscouriertracking.domain.courier.model.Courier;
import com.ataberkcanitez.migroscouriertracking.domain.location.model.Location;
import com.ataberkcanitez.migroscouriertracking.infra.adapters.courier.jpa.entity.CourierEntity;
import com.ataberkcanitez.migroscouriertracking.infra.adapters.courier.jpa.repository.CourierJpaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CourierDataAdapterTest {

    @Mock
    private CourierJpaRepository courierJpaRepository;

    @InjectMocks
    private CourierDataAdapter courierDataAdapter;

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
