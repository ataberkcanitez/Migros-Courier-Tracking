package com.ataberkcanitez.migroscouriertracking.infra.adapters.store.jpa;

import com.ataberkcanitez.migroscouriertracking.domain.store.model.Store;
import com.ataberkcanitez.migroscouriertracking.domain.store.port.StorePort;
import com.ataberkcanitez.migroscouriertracking.infra.adapters.store.jpa.entity.StoreEntity;
import com.ataberkcanitez.migroscouriertracking.infra.adapters.store.jpa.repository.StoreJpaRepository;
import com.ataberkcanitez.migroscouriertracking.infra.adapters.store.rest.dto.CreateStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreDataAdapter implements StorePort {
    private final StoreJpaRepository storeJpaRepository;

    @Override
    public Store save(CreateStore store) {
        StoreEntity storeEntity = new StoreEntity();
        storeEntity.setName(store.getName());
        storeEntity.setLatitude(store.getLocation().getLat());
        storeEntity.setLongitude(store.getLocation().getLon());

        return storeJpaRepository.save(storeEntity).toModel();
    }

    @Override
    public List<Store> findAll() {
        return storeJpaRepository.findAll().stream().map(StoreEntity::toModel).toList();
    }

    @Override
    public Store findById(Long id) {
        return storeJpaRepository.findById(id).orElseThrow(() -> new RuntimeException("Store not found")).toModel();
    }

    @Override
    public void removeById(Long id) {
        storeJpaRepository.deleteById(id);
    }
}
