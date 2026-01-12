package org.docencia.hotel.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class HotelTest {

    @Test
    void testConstructorsAndAccessors() {
        Hotel hotel = new Hotel();
        hotel.setId(1L);
        hotel.setName("Grand Hotel");
        hotel.setAddress("123 Main St");
        Set<Room> rooms = new HashSet<>();
        hotel.setRooms(rooms);

        assertEquals(1L, hotel.getId());
        assertEquals("Grand Hotel", hotel.getName());
        assertEquals("123 Main St", hotel.getAddress());
        assertEquals(rooms, hotel.getRooms());

        Hotel hotel2 = new Hotel(1L, "Grand Hotel", "123 Main St", rooms);
        assertEquals(hotel, hotel2);

        Hotel hotel3 = new Hotel(1L);
        assertEquals(1L, hotel3.getId());
    }
}
