package com.ataberkcanitez.migroscouriertracking.domain.store.service;

import com.ataberkcanitez.migroscouriertracking.domain.location.model.Location;
import com.ataberkcanitez.migroscouriertracking.domain.store.model.Store;
import com.ataberkcanitez.migroscouriertracking.domain.store.port.StorePort;
import com.ataberkcanitez.migroscouriertracking.infra.adapters.courier.websocket.CourierLocationSubject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StoreEntranceHandlerTest {

    @Mock
    private CourierLocationSubject courierLocationSubject;

    @Mock
    private StorePort storePort;

    @InjectMocks
    private StoreEntranceHandler storeEntranceHandler;

    private final double courierLatitude = 40.730610;
    private final double courierLongitude = -73.935242;

    private final double storeLatitude = 40.733796;
    private final double storeLongitude = -73.991501;

    private final Location courierLocation = new Location(courierLatitude, courierLongitude);
    private final Location storeLocation = new Location(storeLatitude, storeLongitude);

    @Test
    public void shouldAttachObserverToCourierLocationSubject() {
        storeEntranceHandler.init();
        verify(courierLocationSubject).attachObserver(storeEntranceHandler);
    }

    @Test
    public void receiveLocationUpdate_shouldFindNearbyStoreAndLogEntry() throws Exception {
        // Given
        long courierId = 1L;
        when(storePort.findNearbyStoresAndNotEnteredRecently(eq(courierId), any(Location.class)))
                .thenReturn(Store.builder().id(1L).name("Test Store").location(storeLocation).build());

        // When
        storeEntranceHandler.receiveLocationUpdate(courierId, courierLocation);

        // Then
        verify(storePort).findNearbyStoresAndNotEnteredRecently(eq(courierId), any(Location.class));
        verify(storePort).saveEntrance(eq(courierId), eq(1L));
        verifyNoMoreInteractions(storePort, courierLocationSubject);
    }

    @Test
    public void receiveLocationUpdate_shouldNotFindNearbyStoreAndLogWarning() throws Exception {
        // Given
        long courierId = 1L;
        when(storePort.findNearbyStoresAndNotEnteredRecently(eq(courierId), any(Location.class)))
                .thenThrow(new RuntimeException());

        // When
        storeEntranceHandler.receiveLocationUpdate(courierId, courierLocation);

        // Then
        verify(storePort).findNearbyStoresAndNotEnteredRecently(eq(courierId), any(Location.class));
        verifyNoMoreInteractions(storePort, courierLocationSubject);
    }
}
