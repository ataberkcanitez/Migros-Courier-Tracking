package com.ataberkcanitez.migroscouriertracking.infra.adapters.courier.rest;

import com.ataberkcanitez.migroscouriertracking.domain.courier.service.CourierService;
import com.ataberkcanitez.migroscouriertracking.infra.adapters.courier.rest.dto.CourierTotalDistanceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/courier")
@RequiredArgsConstructor
public class CourierController {
    private final CourierService courierService;

    @GetMapping("/{courierId}/total-travel-distance")
    public ResponseEntity<CourierTotalDistanceResponse> getTotalTravelDistance(@PathVariable long courierId) {
        return ResponseEntity.ok(courierService.getTotalTravelDistance(courierId));
    }
}