package com.ataberkcanitez.migroscouriertracking.domain.store.service;

import com.ataberkcanitez.migroscouriertracking.domain.location.model.Location;
import com.ataberkcanitez.migroscouriertracking.domain.store.exception.StoreCreateException;
import com.ataberkcanitez.migroscouriertracking.domain.store.model.Store;
import com.ataberkcanitez.migroscouriertracking.domain.store.port.StorePort;
import com.ataberkcanitez.migroscouriertracking.infra.adapters.store.rest.dto.CreateStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MigrosStoreTest {

    private MigrosStore migrosStore;

    @Mock
    private StorePort storePort;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        migrosStore = new MigrosStore(storePort);
    }

    @Test
    void testSave() {
        // given
        CreateStore createStore = new CreateStore();
        createStore.setName("Test Store");
        Location location = new Location();
        location.setLat(40.7128);
        location.setLon(-74.0060);
        createStore.setLocation(location);

        Store store = new Store();
        store.setId(1L);
        store.setName("Test Store");
        store.setLocation(location);

        when(storePort.save(createStore)).thenReturn(store);

        // when
        Store savedStore = migrosStore.save(createStore);

        // then
        assertNotNull(savedStore);
        assertEquals(store, savedStore);
        verify(storePort, times(1)).save(createStore);
    }

    @Test
    void testSaveWithException() {
        // given
        CreateStore createStore = new CreateStore();
        createStore.setName("Test Store");
        Location location = new Location();
        location.setLat(40.7128);
        location.setLon(-74.0060);
        createStore.setLocation(location);

        when(storePort.save(createStore)).thenThrow(new RuntimeException());

        // then
        assertThrows(StoreCreateException.class, () -> {
            // when
            migrosStore.save(createStore);
        });

        verify(storePort, times(1)).save(createStore);
    }

    @Test
    void testFindById() {
        // given
        Store store = new Store();
        store.setId(1L);
        store.setName("Test Store");
        Location location = new Location();
        location.setLat(40.7128);
        location.setLon(-74.0060);
        store.setLocation(location);

        when(storePort.findById(1L)).thenReturn(store);

        // when
        Store foundStore = migrosStore.findById(1L);

        // then
        assertNotNull(foundStore);
        assertEquals(store, foundStore);
        verify(storePort, times(1)).findById(1L);
    }

    @Test
    void testFindByIdWithException() {
        // given
        when(storePort.findById(1L)).thenThrow(new RuntimeException());

        // when
        Store foundStore = migrosStore.findById(1L);

        // then
        assertNull(foundStore);
        verify(storePort, times(1)).findById(1L);
    }

    @Test
    void testRemoveById() {
        // when
        migrosStore.removeById(1L);

        // then
        verify(storePort, times(1)).removeById(1L);
    }
}
