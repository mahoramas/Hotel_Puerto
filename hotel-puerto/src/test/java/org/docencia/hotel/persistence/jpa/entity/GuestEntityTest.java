package org.docencia.hotel.persistence.jpa.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.docencia.hotel.persistence.nosql.document.GuestPreferencesDocument;
import org.junit.jupiter.api.Test;

class GuestEntityTest {

    @Test
    void testConstructorsAndAccessors() {
        GuestEntity entity = new GuestEntity();
        entity.setId(1L);
        entity.setFullName("Jane Doe");
        entity.setEmail("jane@example.com");
        entity.setPhone("987654321");
        Set<BookingEntity> bookings = new HashSet<>();
        entity.setBookings(bookings);
        GuestPreferencesDocument pref = new GuestPreferencesDocument();
        entity.setPreference(pref);

        assertEquals(1L, entity.getId());
        assertEquals("Jane Doe", entity.getFullName());
        assertEquals("jane@example.com", entity.getEmail());
        assertEquals("987654321", entity.getPhone());
        assertEquals(bookings, entity.getBookings());
        assertEquals(pref, entity.getPreference());

        GuestEntity entity2 = new GuestEntity(bookings, "jane@example.com", "Jane Doe", 1L, "987654321", pref);
        assertEquals(entity, entity2);

        GuestEntity entity3 = new GuestEntity(1L);
        assertEquals(1L, entity3.getId());
    }
}
