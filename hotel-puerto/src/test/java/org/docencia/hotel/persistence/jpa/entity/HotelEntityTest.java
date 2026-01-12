package org.docencia.hotel.persistence.jpa.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class HotelEntityTest {

    @Test
    void testConstructorsAndAccessors() {
        HotelEntity entity = new HotelEntity();
        entity.setId(1L);
        entity.setName("Plaza");
        entity.setAddress("City Center");
        List<RoomEntity> rooms = new ArrayList<>();
        entity.setRooms(rooms);

        assertEquals(1L, entity.getId());
        assertEquals("Plaza", entity.getName());
        assertEquals("City Center", entity.getAddress());
        assertEquals(rooms, entity.getRooms());

        HotelEntity entity2 = new HotelEntity(1L, "Plaza", "City Center", rooms);
        assertEquals(entity, entity2);

        HotelEntity entity3 = new HotelEntity(1L);
        assertEquals(1L, entity3.getId());

        entity.id(2L).name("Ritz").address("Uptown").rooms(rooms);
        assertEquals(2L, entity.getId());
        assertEquals("Ritz", entity.getName());
    }
}
