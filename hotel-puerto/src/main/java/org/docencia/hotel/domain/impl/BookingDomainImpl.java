package org.docencia.hotel.domain.impl;

import java.util.Set;

import org.docencia.hotel.domain.api.BookingDomain;
import org.docencia.hotel.domain.model.Booking;
import org.docencia.hotel.service.api.BookingService;
import org.springframework.stereotype.Service;

@Service
public class BookingDomainImpl implements BookingDomain {

    private final BookingService service;

    public BookingDomainImpl(BookingService service) {
        this.service = service;
    }

    @Override
    public Set<Booking> findAll() {
        return service.findAll();
    }

    @Override
    public Booking findById(long id) {
        return service.findById(id);
    }

    @Override
    public Booking save(Booking booking) {
        return service.save(booking);
    }

    @Override
    public Boolean deleteById(long id) {
        return service.deleteById(id);
    }
}
