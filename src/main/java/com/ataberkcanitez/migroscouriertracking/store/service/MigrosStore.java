package com.ataberkcanitez.migroscouriertracking.store.service;

import com.ataberkcanitez.migroscouriertracking.store.exception.StoreCreateException;
import com.ataberkcanitez.migroscouriertracking.store.model.Store;
import com.ataberkcanitez.migroscouriertracking.store.port.StorePort;
import com.ataberkcanitez.migroscouriertracking.store.adapter.rest.dto.CreateStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MigrosStore implements StoreService {
    private final StorePort storePort;

    @Override
    public Store save(CreateStore createStore) {
        try {
            Store store = storePort.save(createStore);

            log.info("Store saved successfully with id: {}", store.getId());
            return store;
        } catch (Exception e) {
            log.error("Error while saving store");
            throw new StoreCreateException("error while saving store");
        }
    }

    @Override
    public Store findById(Long id) {
        try {
            return storePort.findById(id);
        } catch (RuntimeException e) {
            log.error("Error {} for store with id: {}", e.getMessage(),  id);
            return null;
        }
    }

    @Override
    public void removeById(Long id) {
        storePort.removeById(id);
    }
}
