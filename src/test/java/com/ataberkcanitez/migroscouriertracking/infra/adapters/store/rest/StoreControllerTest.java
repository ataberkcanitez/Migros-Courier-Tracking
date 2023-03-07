package com.ataberkcanitez.migroscouriertracking.infra.adapters.store.rest;

import com.ataberkcanitez.migroscouriertracking.domain.location.model.Location;
import com.ataberkcanitez.migroscouriertracking.domain.store.model.Store;
import com.ataberkcanitez.migroscouriertracking.domain.store.port.StorePort;
import com.ataberkcanitez.migroscouriertracking.domain.store.service.MigrosStore;
import com.ataberkcanitez.migroscouriertracking.domain.store.service.StoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class StoreControllerTest {
    @Mock
    private StorePort storePort;
    @Mock
    private StoreService storeService;

    private StoreController storeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        storeService = new MigrosStore(storePort);
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
}
