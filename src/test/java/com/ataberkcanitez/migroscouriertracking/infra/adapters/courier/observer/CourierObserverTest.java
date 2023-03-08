package com.ataberkcanitez.migroscouriertracking.infra.adapters.courier.observer;

import static org.junit.jupiter.api.Assertions.*;

import com.ataberkcanitez.migroscouriertracking.domain.common.observer.CourierLocationSubscriber;
import com.ataberkcanitez.migroscouriertracking.domain.location.model.Location;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CourierObserverTest {

    @Mock
    private CourierLocationSubscriber subscriber1;
    @Mock
    private CourierLocationSubscriber subscriber2;

    @InjectMocks
    private CourierObserver courierObserver;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAttachObserver() {
        courierObserver.attachObserver(subscriber1);
        courierObserver.attachObserver(subscriber2);
        assertEquals(2, courierObserver.getObservers().size());
    }

    @Test
    public void testDetachObserver() {
        courierObserver.attachObserver(subscriber1);
        courierObserver.attachObserver(subscriber2);
        courierObserver.detachObserver(subscriber2);
        assertEquals(1, courierObserver.getObservers().size());
    }

    @Test
    public void testNotifyObservers() {
        courierObserver.attachObserver(subscriber1);
        courierObserver.attachObserver(subscriber2);

        long courierId = 1L;
        Location location = new Location(51.5074, 0.1278);
        courierObserver.notifyObservers(courierId, location);

        verify(subscriber1).receiveLocationUpdate(courierId, location);
        verify(subscriber2).receiveLocationUpdate(courierId, location);
    }
}