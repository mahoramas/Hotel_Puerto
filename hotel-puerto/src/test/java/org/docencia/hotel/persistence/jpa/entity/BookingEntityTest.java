package org.docencia.hotel.persistence.jpa.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class BookingEntityTest {

    @Test
    void testConstructorsAndAccessors() {
        BookingEntity entity = new BookingEntity();
        entity.setId(1L);
        RoomEntity room = new RoomEntity();
        entity.setRoomId(room);
        GuestEntity guest = new GuestEntity();
        entity.setGuestId(guest);
        String date = "2025-01-01";
        entity.setCheckIn(date);
        entity.setCheckOut(date);

        assertEquals(1L, entity.getId());
        assertEquals(room, entity.getRoomId());
        assertEquals(guest, entity.getGuestId());
        assertEquals(date, entity.getCheckIn());

        BookingEntity entity2 = new BookingEntity(date, date, guest, 1L, room);
        assertEquals(entity, entity2);

        BookingEntity entity3 = new BookingEntity(1L);
        assertEquals(1L, entity3.getId());
    }
}
