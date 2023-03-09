package com.ataberkcanitez.migroscouriertracking.store.service;

import com.ataberkcanitez.migroscouriertracking.store.model.Store;
import com.ataberkcanitez.migroscouriertracking.store.adapter.rest.dto.CreateStore;

public interface StoreService {
    Store save(CreateStore createStore);
    Store findById(Long id);
    void removeById(Long id);
}
