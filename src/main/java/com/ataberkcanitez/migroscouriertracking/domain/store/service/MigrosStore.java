package com.ataberkcanitez.migroscouriertracking.domain.store.service;

import com.ataberkcanitez.migroscouriertracking.domain.store.exception.StoreCreateException;
import com.ataberkcanitez.migroscouriertracking.domain.store.model.Store;
import com.ataberkcanitez.migroscouriertracking.domain.store.port.StorePort;
import com.ataberkcanitez.migroscouriertracking.infra.adapters.store.rest.dto.CreateStore;
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
            log.error("Error while saving store", e);
            throw new StoreCreateException("error while saving store");
        }
    }

    @Override
    public Store findById(Long id) {
        try {
            return storePort.findById(id);
        } catch (Exception e) {
            log.error("Error while finding store with id: {}", id, e);
            return null;
        }
    }

    @Override
    public void removeById(Long id) {
        storePort.removeById(id);
    }
}
