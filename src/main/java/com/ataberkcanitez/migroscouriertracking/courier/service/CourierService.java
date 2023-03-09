package com.ataberkcanitez.migroscouriertracking.courier.service;

import com.ataberkcanitez.migroscouriertracking.courier.adapter.rest.dto.CourierTotalDistanceResponse;

public interface CourierService {
    CourierTotalDistanceResponse getTotalTravelDistance(long courierId);
}