package org.docencia.hotel.service.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.docencia.hotel.domain.model.Hotel;
import org.docencia.hotel.mapper.jpa.HotelMapper;
import org.docencia.hotel.persistence.repository.jpa.HotelRepository;
import org.docencia.hotel.service.api.HotelService;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

/**
 * @author mahoramas
 * @version 1.0.0
 */
@Service
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;

    public HotelServiceImpl(HotelRepository hotelRepository, HotelMapper hotelMapper) {
        this.hotelRepository = hotelRepository;
        this.hotelMapper = hotelMapper;
    }

    @Override
    public Set<Hotel> findAll() {
        return hotelMapper.toDomain(new HashSet<>(hotelRepository.findAll()));
    }

    @Override
    public Hotel findById(long id) {
        return hotelMapper.toDomain(hotelRepository.findById(id).orElse(null));
    }

    @Override
    @Transactional
    public Hotel save(Hotel hotel) {
        if (hotel.getId() == null) {
            hotel.setId(UUID.randomUUID().getMostSignificantBits());
        }
        return hotelMapper.toDomain(hotelRepository.save(hotelMapper.toEntity(hotel)));
    }

    @Override
    @Transactional
    public Boolean deleteById(long id) {
        if (!hotelRepository.existsById(id)) {
            return false;
        }
        hotelRepository.deleteById(id);
        return true;
    }

}
