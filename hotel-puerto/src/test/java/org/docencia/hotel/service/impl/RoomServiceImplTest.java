package org.docencia.hotel.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.docencia.hotel.domain.model.Room;
import org.docencia.hotel.mapper.jpa.RoomMapper;
import org.docencia.hotel.persistence.jpa.entity.RoomEntity;
import org.docencia.hotel.persistence.repository.jpa.RoomRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.anySet;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RoomServiceImplTest {

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private RoomMapper roomMapper;

    @InjectMocks
    private RoomServiceImpl roomService;

    @Test
    void findAllTestReturnSet() {
        RoomEntity entity = new RoomEntity();
        entity.setId(1L);

        Room room = new Room();
        room.setId(1L);

        when(roomRepository.findAll()).thenReturn(List.of(entity));
        when(roomMapper.toDomain(anySet())).thenReturn(Set.of(room));

        Set<Room> result = roomService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(roomRepository).findAll();
    }

    @Test
    void findByIdTestReturnRoom() {
        long id = 1L;
        RoomEntity entity = new RoomEntity();
        entity.setId(id);

        Room room = new Room();
        room.setId(id);

        when(roomRepository.findById(id)).thenReturn(Optional.of(entity));
        when(roomMapper.toDomain(entity)).thenReturn(room);

        Room result = roomService.findById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
    }

    @Test
    void findByIdTestReturnNull() {
        long id = 1L;
        when(roomRepository.findById(id)).thenReturn(Optional.empty());
        when(roomMapper.toDomain((RoomEntity) null)).thenReturn(null);

        Room result = roomService.findById(id);

        assertNull(result);
    }

    @Test
    void saveTest() {
        Room room = new Room();
        RoomEntity entity = new RoomEntity();
        RoomEntity savedEntity = new RoomEntity();
        savedEntity.setId(1L);
        Room savedRoom = new Room();
        savedRoom.setId(1L);

        when(roomMapper.toEntity(room)).thenReturn(entity);
        when(roomRepository.save(entity)).thenReturn(savedEntity);
        when(roomMapper.toDomain(savedEntity)).thenReturn(savedRoom);

        Room result = roomService.save(room);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void saveTestReturnRoom() {
        Room room = new Room();
        room.setId(1L);
        RoomEntity entity = new RoomEntity();
        entity.setId(1L);
        RoomEntity savedEntity = new RoomEntity();
        savedEntity.setId(1L);
        Room savedRoom = new Room();
        savedRoom.setId(1L);

        when(roomMapper.toEntity(room)).thenReturn(entity);
        when(roomRepository.save(entity)).thenReturn(savedEntity);
        when(roomMapper.toDomain(savedEntity)).thenReturn(savedRoom);

        Room result = roomService.save(room);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void deleteByIdTest() {
        long id = 1L;
        when(roomRepository.existsById(id)).thenReturn(true);
        doNothing().when(roomRepository).deleteById(id);

        Boolean result = roomService.deleteById(id);

        assertTrue(result);
        verify(roomRepository).deleteById(id);
    }

    @Test
    void deleteByIdTestReturnFalse() {
        long id = 1L;
        when(roomRepository.existsById(id)).thenReturn(false);

        Boolean result = roomService.deleteById(id);

        assertFalse(result);
        verify(roomRepository, times(0)).deleteById(id);
    }

    @Test
    void findByHotelIdTestReturnRoom() {
        Long hotelId = 100L;
        RoomEntity entity = new RoomEntity();
        entity.setId(1L);

        Room room = new Room();
        room.setId(1L);

        when(roomRepository.findByHotelId(hotelId)).thenReturn(List.of(entity));
        when(roomMapper.toDomain(entity)).thenReturn(room);

        Room result = roomService.findByHotelId(hotelId);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(roomRepository).findByHotelId(hotelId);
    }
}
