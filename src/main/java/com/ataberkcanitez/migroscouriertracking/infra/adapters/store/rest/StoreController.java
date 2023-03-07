package com.ataberkcanitez.migroscouriertracking.infra.adapters.store.rest;

import com.ataberkcanitez.migroscouriertracking.domain.store.model.Store;
import com.ataberkcanitez.migroscouriertracking.domain.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/stores")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @GetMapping("/{id}")
    public ResponseEntity<Store> getStoreById(@PathVariable Long id) {
        Store store = storeService.findById(id);
        if (Objects.isNull(store)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(store);
    }
}
