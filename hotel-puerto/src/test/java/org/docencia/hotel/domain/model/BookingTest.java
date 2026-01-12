package org.docencia.hotel.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class BookingTest {

    @Test
    void testConstructorsAndAccessors() {
        Booking booking = new Booking();
        assertNotNull(booking);

        booking.setId(1L);
        Room room = new Room();
        booking.setRoom(room);
        Guest guest = new Guest();
        booking.setGuest(guest);
        LocalDate date = LocalDate.now();
        booking.setCheckIn(date);
        booking.setCheckOut(date);

        assertEquals(1L, booking.getId());
        assertEquals(room, booking.getRoom());
        assertEquals(guest, booking.getGuest());
        assertEquals(date, booking.getCheckIn());
        assertEquals(date, booking.getCheckOut());

        Booking booking2 = new Booking(1L, room, guest, date, date);
        assertEquals(booking, booking2);

        Booking booking3 = new Booking(1L);
        assertEquals(1L, booking3.getId());

        assertNotNull(booking.toString());
        assertEquals(booking.hashCode(), booking2.hashCode());
        assertNotEquals(booking, new Booking(2L));
    }
}
