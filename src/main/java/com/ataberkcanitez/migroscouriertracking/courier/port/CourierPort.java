package com.ataberkcanitez.migroscouriertracking.courier.port;

import com.ataberkcanitez.migroscouriertracking.courier.model.Courier;

public interface CourierPort {
    Courier getCourierById(long courierId);
}