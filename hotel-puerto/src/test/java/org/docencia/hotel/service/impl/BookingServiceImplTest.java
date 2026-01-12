package org.docencia.hotel.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.docencia.hotel.domain.model.Booking;
import org.docencia.hotel.mapper.jpa.BookingMapper;
import org.docencia.hotel.persistence.jpa.entity.BookingEntity;
import org.docencia.hotel.persistence.repository.jpa.BookingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BookingServiceImplTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private BookingMapper bookingMapper;

    @InjectMocks
    private BookingServiceImpl bookingService;

    @Test
    void findAllTestReturnSet() {
        BookingEntity entity = new BookingEntity();
        entity.setId(1L);

        Booking booking = new Booking();
        booking.setId(1L);

        when(bookingRepository.findAll()).thenReturn(List.of(entity));
        when(bookingMapper.toDomain(anySet())).thenReturn(Set.of(booking));

        Set<Booking> result = bookingService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(bookingRepository).findAll();
    }

    @Test
    void findByIdTestReturnBooking() {
        long id = 1L;
        BookingEntity entity = new BookingEntity();
        entity.setId(id);

        Booking booking = new Booking();
        booking.setId(id);

        when(bookingRepository.findById(id)).thenReturn(Optional.of(entity));
        when(bookingMapper.toDomain(entity)).thenReturn(booking);

        Booking result = bookingService.findById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
    }

    @Test
    void findByIdTestReturnNull() {
        long id = 1L;
        when(bookingRepository.findById(id)).thenReturn(Optional.empty());
        when(bookingMapper.toDomain((BookingEntity) null)).thenReturn(null);

        Booking result = bookingService.findById(id);

        assertNull(result);
    }

    @Test
    void saveTestReturnBooking() {
        Booking booking = new Booking();
        BookingEntity entity = new BookingEntity();
        BookingEntity savedEntity = new BookingEntity();
        savedEntity.setId(1L);
        Booking savedBooking = new Booking();
        savedBooking.setId(1L);

        when(bookingMapper.toEntity(booking)).thenReturn(entity);
        when(bookingRepository.save(entity)).thenReturn(savedEntity);
        when(bookingMapper.toDomain(savedEntity)).thenReturn(savedBooking);

        Booking result = bookingService.save(booking);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void saveTestUpdateBooking() {
        Booking booking = new Booking();
        booking.setId(1L);
        BookingEntity entity = new BookingEntity();
        entity.setId(1L);
        BookingEntity savedEntity = new BookingEntity();
        savedEntity.setId(1L);
        Booking savedBooking = new Booking();
        savedBooking.setId(1L);

        when(bookingMapper.toEntity(booking)).thenReturn(entity);
        when(bookingRepository.save(entity)).thenReturn(savedEntity);
        when(bookingMapper.toDomain(savedEntity)).thenReturn(savedBooking);

        Booking result = bookingService.save(booking);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void deleteByIdTest() {
        long id = 1L;
        when(bookingRepository.existsById(id)).thenReturn(true);
        doNothing().when(bookingRepository).deleteById(id);

        Boolean result = bookingService.deleteById(id);

        assertTrue(result);
        verify(bookingRepository).deleteById(id);
    }

    @Test
    void deleteByIdFalseTest() {
        long id = 1L;
        when(bookingRepository.existsById(id)).thenReturn(false);

        Boolean result = bookingService.deleteById(id);

        assertFalse(result);
        verify(bookingRepository, times(0)).deleteById(id);
    }
}
