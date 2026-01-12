package org.docencia.hotel.web.soap.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Set;

import org.docencia.hotel.domain.api.BookingDomain;
import org.docencia.hotel.domain.model.Booking;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BookingSoapServiceImplTest {

    @Mock
    private BookingDomain bookingDomain;

    @InjectMocks
    private BookingSoapServiceImpl bookingSoapService;

    @Test
    void getBookingByIdTestReturnBooking() {
        Long id = 1L;
        Booking booking = new Booking();
        when(bookingDomain.findById(id)).thenReturn(booking);

        Booking result = bookingSoapService.getBookingById(id);

        assertEquals(booking, result);
        verify(bookingDomain).findById(id);
    }

    @Test
    void saveBookingTestReturnBooking() {
        Booking booking = new Booking();
        when(bookingDomain.save(booking)).thenReturn(booking);

        Booking result = bookingSoapService.saveBooking(booking);

        assertEquals(booking, result);
        verify(bookingDomain).save(booking);
    }

    @Test
    void findAllBookingTestReturnSet() {
        Set<Booking> bookings = Set.of(new Booking());
        when(bookingDomain.findAll()).thenReturn(bookings);

        Set<Booking> result = bookingSoapService.findAllBooking();

        assertEquals(bookings, result);
        verify(bookingDomain).findAll();
    }

    @Test
    void deleteBookingByIdTestReturnTrue() {
        Long id = 1L;
        when(bookingDomain.deleteById(id)).thenReturn(true);

        boolean result = bookingSoapService.deleteBookingById(id);

        assertTrue(result);
        verify(bookingDomain).deleteById(id);
    }
}
