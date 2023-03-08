package com.ataberkcanitez.migroscouriertracking.infra.adapters.store.jpa.repository;

import com.ataberkcanitez.migroscouriertracking.infra.adapters.store.jpa.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreJpaRepository extends JpaRepository<StoreEntity, Long> {
    @Query(value = "SELECT s.id, s.name, s.latitude, s.longitude " +
            "FROM stores s " +
            "LEFT JOIN entrances e ON e.store_id = s.id AND e.entrance_date >= DATE_SUB(NOW(), INTERVAL 1 MINUTE) AND e.courier_id = :courierId " +
            "WHERE ( " +
            "  6371000 * 2 * ASIN(SQRT( " +
            "    POWER(SIN(RADIANS((:latitude - s.latitude) / 2)), 2) + " +
            "    COS(RADIANS(:latitude)) * COS(RADIANS(s.latitude)) * " +
            "    POWER(SIN(RADIANS((:longitude - s.longitude) / 2)), 2) " +
            "  ))) <= 100 " +
            "AND e.id IS NULL " +
            "LIMIT 1", nativeQuery = true)
    Optional<StoreEntity> findNearbyStoresAndNotEnteredRecently(@Param("latitude") Double latitude, @Param("longitude") Double longitude, @Param("courierId") Long courierId);
}
