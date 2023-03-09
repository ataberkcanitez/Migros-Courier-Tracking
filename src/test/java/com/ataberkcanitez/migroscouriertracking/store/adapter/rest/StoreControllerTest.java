package com.ataberkcanitez.migroscouriertracking.store.adapter.rest;

import com.ataberkcanitez.migroscouriertracking.location.model.Location;
import com.ataberkcanitez.migroscouriertracking.store.model.Store;
import com.ataberkcanitez.migroscouriertracking.store.port.StorePort;
import com.ataberkcanitez.migroscouriertracking.store.service.StoreService;
import com.ataberkcanitez.migroscouriertracking.store.adapter.rest.dto.CreateStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StoreControllerTest {
    @Mock
    private StorePort storePort;
    @Mock
    private StoreService storeService;

    private StoreController storeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        storeController = new StoreController(storeService);
    }

    @Test
    void testGetStoreById() {
        // given
        Long id = 1L;
        Store store = Store.builder().id(id).name("Test Store").location(new Location(0, 0)).build();
        when(storeService.findById(id)).thenReturn(store);

        // when
        ResponseEntity<Store> responseEntity = storeController.getStoreById(id);

        // then
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(store, responseEntity.getBody());
    }

    @Test
    void testGetStoreByIdNotFound() {
        // given
        Long id = 1L;
        when(storeService.findById(id)).thenReturn(null);

        // when
        ResponseEntity<Store> responseEntity = storeController.getStoreById(id);

        // then
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }
    @Test
    public void testCreateStore() {
        // given
        CreateStore createStore = new CreateStore("MyStore", new Location(51.5074, 0.1278));
        when(storeService.save(createStore)).thenReturn(new Store(1L, createStore.getName(), createStore.getLocation()));

        // when
        ResponseEntity<Void> response = storeController.createStore(createStore);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testDeleteStore() {
        // given
        Long storeId = 1L;
        storeService = mock(StoreService.class);
        doNothing().when(storeService).removeById(storeId);

        // when
        ResponseEntity<Void> response = storeController.deleteStore(storeId);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testDeleteNonExistentStore() {
        // given
        Long storeId = 1L;
        doNothing().when(storeService).removeById(storeId);
        when(storeService.findById(storeId)).thenReturn(null);

        // when
        ResponseEntity<Void> response = storeController.deleteStore(storeId);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
