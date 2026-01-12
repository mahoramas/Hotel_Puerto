package org.docencia.hotel.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class GuestTest {

    @Test
    void testConstructorsAndAccessors() {
        Guest guest = new Guest();
        assertNotNull(guest);

        guest.setId(1L);
        guest.setFullName("John Doe");
        guest.setEmail("john@example.com");
        guest.setPhone("123456789");
        GuestPreferences prefs = new GuestPreferences();
        guest.setPreference(prefs);
        Set<Booking> bookings = new HashSet<>();
        guest.setBookings(bookings);

        assertEquals(1L, guest.getId());
        assertEquals("John Doe", guest.getFullName());
        assertEquals("john@example.com", guest.getEmail());
        assertEquals("123456789", guest.getPhone());
        assertEquals(prefs, guest.getPreference());
        assertEquals(bookings, guest.getBookings());

        Guest guest2 = new Guest(1L, "John Doe", "john@example.com", "123456789", bookings, prefs);
        assertEquals(guest, guest2);

        Guest guest3 = new Guest(1L);
        assertEquals(1L, guest3.getId());
    }
}
