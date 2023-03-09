package com.ataberkcanitez.migroscouriertracking.store.adapter.rest;

import com.ataberkcanitez.migroscouriertracking.store.model.Store;
import com.ataberkcanitez.migroscouriertracking.store.service.StoreService;
import com.ataberkcanitez.migroscouriertracking.store.adapter.rest.dto.CreateStore;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<Void> createStore(@Valid @RequestBody CreateStore createStore) {
        storeService.save(createStore);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStore(@PathVariable Long id) {
        storeService.removeById(id);
        return ResponseEntity.ok().build();
    }
}
