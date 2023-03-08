package com.ataberkcanitez.migroscouriertracking.infra.adapters.store.jpa;

import com.ataberkcanitez.migroscouriertracking.domain.location.model.Location;
import com.ataberkcanitez.migroscouriertracking.domain.store.model.Store;
import com.ataberkcanitez.migroscouriertracking.infra.adapters.store.jpa.entity.StoreEntity;
import com.ataberkcanitez.migroscouriertracking.infra.adapters.store.jpa.entity.StoreEntranceEntity;
import com.ataberkcanitez.migroscouriertracking.infra.adapters.store.jpa.repository.StoreEntranceJpaRepository;
import com.ataberkcanitez.migroscouriertracking.infra.adapters.store.jpa.repository.StoreJpaRepository;
import com.ataberkcanitez.migroscouriertracking.infra.adapters.store.rest.dto.CreateStore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StoreDataAdapterTest {

    @Mock
    private StoreJpaRepository storeJpaRepository;

    @Mock
    private StoreEntranceJpaRepository storeEntranceJpaRepository;

    @InjectMocks
    private StoreDataAdapter storeDataAdapter;

    @Test
    public void testSave() {
        // given
        CreateStore createStore = new CreateStore("Test Store", new Location(1.0, 2.0));
        StoreEntity storeEntity = new StoreEntity(1L, createStore.getName(), createStore.getLocation().getLat(), createStore.getLocation().getLon());
        when(storeJpaRepository.save(any(StoreEntity.class))).thenReturn(storeEntity);

        // when
        Store savedStore = storeDataAdapter.save(createStore);

        // then
        assertNotNull(savedStore);
        assertEquals(storeEntity.getId(), savedStore.getId());
        assertEquals(storeEntity.getName(), savedStore.getName());
        assertEquals(storeEntity.getLatitude(), savedStore.getLocation().getLat());
        assertEquals(storeEntity.getLongitude(), savedStore.getLocation().getLon());
    }

    @Test
    public void testFindAll() {
        // given
        StoreEntity storeEntity = new StoreEntity(1L, "Test Store", 1.0, 2.0);
        when(storeJpaRepository.findAll()).thenReturn(Collections.singletonList(storeEntity));

        // when
        List<Store> stores = storeDataAdapter.findAll();

        // then
        assertNotNull(stores);
        assertEquals(1, stores.size());
        assertEquals(storeEntity.getId(), stores.get(0).getId());
        assertEquals(storeEntity.getName(), stores.get(0).getName());
        assertEquals(storeEntity.getLatitude(), stores.get(0).getLocation().getLat());
        assertEquals(storeEntity.getLongitude(), stores.get(0).getLocation().getLon());
    }

    @Test
    public void testFindById() {
        // given
        Long storeId = 1L;
        StoreEntity storeEntity = new StoreEntity(storeId, "Test Store", 1.0, 2.0);
        when(storeJpaRepository.findById(storeId)).thenReturn(Optional.of(storeEntity));

        // when
        Store store = storeDataAdapter.findById(storeId);

        // then
        assertNotNull(store);
        assertEquals(storeEntity.getId(), store.getId());
        assertEquals(storeEntity.getName(), store.getName());
        assertEquals(storeEntity.getLatitude(), store.getLocation().getLat());
        assertEquals(storeEntity.getLongitude(), store.getLocation().getLon());
    }

    @Test(expected = RuntimeException.class)
    public void testFindByIdNotFound() {
        // given
        Long storeId = 1L;

        // when
        when(storeJpaRepository.findById(storeId)).thenReturn(Optional.empty());

        // then
        storeDataAdapter.findById(storeId);
    }

    @Test
    public void testRemoveById() {
        // given
        Long storeId = 1L;

        // when
        storeDataAdapter.removeById(storeId);

        // then
        verify(storeJpaRepository, times(1)).deleteById(storeId);
    }

    @Test
    public void testFindNearbyStoresAndNotEnteredRecentlyReturnsStore() {
        // given
        Double latitude = 37.7749;
        Double longitude = -122.4194;
        Long courierId = 1L;
        Location location = new Location(latitude, longitude);
        StoreEntity storeEntity = new StoreEntity();
        storeEntity.setId(1L);
        storeEntity.setName("Test Store");
        storeEntity.setLatitude(latitude);
        storeEntity.setLongitude(longitude);
        when(storeJpaRepository.findNearbyStoresAndNotEnteredRecently(latitude, longitude, courierId))
                .thenReturn(Optional.of(storeEntity));

        // when
        Store result = storeDataAdapter.findNearbyStoresAndNotEnteredRecently(courierId, location);

        // then
        assertNotNull(result);
        assertEquals(storeEntity.getId(), result.getId());
        assertEquals(storeEntity.getName(), result.getName());
        assertEquals(storeEntity.getLatitude(), result.getLocation().getLat());
        assertEquals(storeEntity.getLongitude(), result.getLocation().getLon());
    }

    @Test
    public void testFindNearbyStoresAndNotEnteredRecentlyThrowsExceptionWhenNoStoreFound() {
        // given
        double latitude = 37.7749;
        double longitude = -122.4194;
        Long courierId = 1L;
        Location location = new Location(latitude, longitude);

        // when
        when(storeJpaRepository.findNearbyStoresAndNotEnteredRecently(latitude, longitude, courierId))
                .thenReturn(Optional.empty());

        //  then
        assertThrows(RuntimeException.class, () -> storeDataAdapter.findNearbyStoresAndNotEnteredRecently(courierId, location));
    }

    @Test
    public void testSaveEntrance() {
        // given
        long courierId = 1L;
        long storeId = 2L;
        LocalDate entranceDate = LocalDate.now();
        StoreEntranceEntity storeEntranceEntity = StoreEntranceEntity.builder()
                .courierId(courierId)
                .storeId(storeId)
                .entranceDate(entranceDate)
                .build();
        doReturn(storeEntranceEntity).when(storeEntranceJpaRepository).save(any());

        // when
        storeDataAdapter.saveEntrance(courierId, storeId);

        // then
        verify(storeEntranceJpaRepository, times(1)).save(any());
    }
}
