package org.docencia.hotel.persistence.repository.jpa;

import org.docencia.hotel.persistence.jpa.entity.GuestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author mahoramas
 * @version 1.0.0
 */
@Repository
public interface GuestJpaRepository extends JpaRepository<GuestEntity, Long> {
}
