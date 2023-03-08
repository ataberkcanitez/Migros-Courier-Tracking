package com.ataberkcanitez.migroscouriertracking.domain.courier.port;

import com.ataberkcanitez.migroscouriertracking.domain.courier.model.Courier;

public interface CourierPort {
    Courier getCourierById(long courierId);
}