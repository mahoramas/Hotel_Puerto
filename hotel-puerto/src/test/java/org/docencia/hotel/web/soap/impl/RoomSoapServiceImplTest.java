package org.docencia.hotel.web.soap.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Set;

import org.docencia.hotel.domain.api.RoomDomain;
import org.docencia.hotel.domain.model.Room;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RoomSoapServiceImplTest {

    @Mock
    private RoomDomain roomDomain;

    @InjectMocks
    private RoomSoapServiceImpl roomSoapService;

    @Test
    void getRoomByIdTestReturnRoom() {
        Long id = 1L;
        Room room = new Room();
        when(roomDomain.findById(id)).thenReturn(room);

        Room result = roomSoapService.getRoomById(id);

        assertEquals(room, result);
    }

    @Test
    void saveRoomTestReturnRoom() {
        Room room = new Room();
        when(roomDomain.save(room)).thenReturn(room);

        Room result = roomSoapService.saveRoom(room);

        assertEquals(room, result);
    }

    @Test
    void findAllRoomsTestReturnSet() {
        Set<Room> rooms = Set.of(new Room());
        when(roomDomain.findAll()).thenReturn(rooms);

        Set<Room> result = roomSoapService.findAllRooms();

        assertEquals(rooms, result);
    }

    @Test
    void deleteRoomByIdTestReturnTrue() {
        Long id = 1L;
        when(roomDomain.deleteById(id)).thenReturn(true);

        boolean result = roomSoapService.deleteRoomById(id);

        assertTrue(result);
    }
}
