package com.ataberkcanitez.migroscouriertracking.courier.adapter.observer;

import com.ataberkcanitez.migroscouriertracking.courier.observer.CourierLocationSubscriber;
import com.ataberkcanitez.migroscouriertracking.location.model.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

public class CourierObserverTest {

    @Mock
    private CourierLocationSubscriber subscriber1;
    @Mock
    private CourierLocationSubscriber subscriber2;

    private CourierObserver courierObserver;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        courierObserver = new CourierObserver();
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
