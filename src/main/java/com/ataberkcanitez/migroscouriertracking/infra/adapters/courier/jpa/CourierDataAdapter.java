package com.ataberkcanitez.migroscouriertracking.infra.adapters.courier.jpa;

import com.ataberkcanitez.migroscouriertracking.domain.courier.model.Courier;
import com.ataberkcanitez.migroscouriertracking.domain.courier.port.CourierPort;
import com.ataberkcanitez.migroscouriertracking.infra.adapters.courier.jpa.repository.CourierJpaRepository;
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
