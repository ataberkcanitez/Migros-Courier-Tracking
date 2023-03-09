package com.ataberkcanitez.migroscouriertracking.courier.adapter.jpa;

import com.ataberkcanitez.migroscouriertracking.courier.model.Courier;
import com.ataberkcanitez.migroscouriertracking.courier.port.CourierPort;
import com.ataberkcanitez.migroscouriertracking.courier.adapter.jpa.repository.CourierJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CourierDataAdapter implements CourierPort {
    private final CourierJpaRepository courierJpaRepository;

    @Override
    public Courier getCourierById(long courierId) {
        return courierJpaRepository.findById(courierId).get().toModel();
    }
}
