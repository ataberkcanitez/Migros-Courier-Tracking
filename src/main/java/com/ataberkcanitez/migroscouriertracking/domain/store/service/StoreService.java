package com.ataberkcanitez.migroscouriertracking.domain.store.service;

import com.ataberkcanitez.migroscouriertracking.domain.store.model.Store;
import com.ataberkcanitez.migroscouriertracking.infra.adapters.store.rest.dto.CreateStore;

public interface StoreService {
    Store save(CreateStore createStore);
    Store findById(Long id);
    void removeById(Long id);
}
