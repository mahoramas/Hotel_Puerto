package org.docencia.hotel.domain.impl;

import java.util.Set;

import org.docencia.hotel.domain.api.HotelDomain;
import org.docencia.hotel.domain.model.Hotel;
import org.docencia.hotel.service.api.HotelService;
import org.springframework.stereotype.Service;

@Service
public class HotelDomainImpl implements HotelDomain {

    private final HotelService service;

    public HotelDomainImpl(HotelService service) {
        this.service = service;
    }

    @Override
    public Set<Hotel> findAll() {
        return service.findAll();
    }

    @Override
    public Hotel findById(long id) {
        return service.findById(id);
    }

    @Override
    public Hotel save(Hotel hotel) {
        return service.save(hotel);
    }

    @Override
    public Boolean deleteById(long id) {
        return service.deleteById(id);
    }

}
