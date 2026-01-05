package org.docencia.hotel.service.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.docencia.hotel.domain.model.Booking;
import org.docencia.hotel.mapper.jpa.BookingMapper;
import org.docencia.hotel.persistence.repository.jpa.BookingRepository;
import org.docencia.hotel.service.api.BookingService;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;


    public BookingServiceImpl(BookingRepository bookingRepository, BookingMapper bookingMapper) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
    }

    @Override
    public Set<Booking> findall() {
       return bookingMapper.toDomain(new HashSet<>(bookingRepository.findAll()));
    }

    @Override
    public Booking findById(long id) {
        return bookingMapper.toDomain(bookingRepository.findById(id).orElse(null));
    }

    @Override
    @Transactional
    public Booking save(Booking booking) {
        if (booking.getId() == null) {
            booking.setId(UUID.randomUUID().getMostSignificantBits());
        }
        return bookingMapper.toDomain(bookingRepository.save(bookingMapper.toEntity(booking)));
    }

    @Override
    @Transactional
    public Boolean deleteById(long id) {
        if (!bookingRepository.existsById(id)) {
            return false;
        }
        bookingRepository.deleteById(id);
        return true;
    }
}
