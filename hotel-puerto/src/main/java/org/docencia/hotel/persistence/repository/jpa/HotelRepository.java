package org.docencia.hotel.persistence.repository.jpa;

import org.docencia.hotel.persistence.jpa.entity.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author mahoramas
 * @version 1.0.0
 */
@Repository
public interface HotelRepository extends JpaRepository<HotelEntity, Long>{
}
