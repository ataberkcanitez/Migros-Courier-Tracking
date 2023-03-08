package com.ataberkcanitez.migroscouriertracking.infra.adapters.store.jpa.repository;

import com.ataberkcanitez.migroscouriertracking.infra.adapters.store.jpa.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreJpaRepository extends JpaRepository<StoreEntity, Long> {
    @Query(value = "SELECT s.* FROM stores s WHERE (6371000 * acos(cos(radians(:latitude)) * cos(radians(s.latitude)) * cos(radians(s.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(s.latitude)))) <= 100 AND NOT EXISTS (SELECT 1 FROM entrances e WHERE e.store_id = s.id AND e.courier_id = :courierId AND e.entrance_date >= DATE_SUB(NOW(), INTERVAL 1 MINUTE)) LIMIT 1", nativeQuery = true)
    Optional<StoreEntity> findNearbyStoresAndNotEnteredRecently(@Param("latitude") Double latitude, @Param("longitude") Double longitude, @Param("courierId") Long courierId);
}
