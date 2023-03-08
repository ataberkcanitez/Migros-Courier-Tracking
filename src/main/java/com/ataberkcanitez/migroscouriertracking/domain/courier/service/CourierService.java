package com.ataberkcanitez.migroscouriertracking.domain.courier.service;

import com.ataberkcanitez.migroscouriertracking.infra.adapters.courier.rest.dto.CourierTotalDistanceResponse;

public interface CourierService {
    CourierTotalDistanceResponse getTotalTravelDistance(long courierId);
}