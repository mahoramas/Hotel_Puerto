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

import org.docencia.hotel.domain.model.Room;
import org.docencia.hotel.mapper.jpa.RoomMapper;
import org.docencia.hotel.persistence.jpa.entity.RoomEntity;
import org.docencia.hotel.persistence.repository.jpa.RoomRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
    void findAll_ShouldReturnSetOfRooms() {
        // Arrange
        RoomEntity entity = new RoomEntity();
        entity.setId(1L);

        Room room = new Room();
        room.setId(1L);

        when(roomRepository.findAll()).thenReturn(List.of(entity));
        when(roomMapper.toDomain(anySet())).thenReturn(Set.of(room));

        // Act
        Set<Room> result = roomService.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(roomRepository).findAll();
    }

    @Test
    void findById_WhenExists_ShouldReturnRoom() {
        // Arrange
        long id = 1L;
        RoomEntity entity = new RoomEntity();
        entity.setId(id);

        Room room = new Room();
        room.setId(id);

        when(roomRepository.findById(id)).thenReturn(Optional.of(entity));
        when(roomMapper.toDomain(entity)).thenReturn(room);

        // Act
        Room result = roomService.findById(id);

        // Assert
        assertNotNull(result);
        assertEquals(id, result.getId());
    }

    @Test
    void findById_WhenNotExists_ShouldReturnNull() {
        // Arrange
        long id = 1L;
        when(roomRepository.findById(id)).thenReturn(Optional.empty());
        when(roomMapper.toDomain((RoomEntity) null)).thenReturn(null);

        // Act
        Room result = roomService.findById(id);

        // Assert
        assertNull(result);
    }

    @Test
    void save_ShouldGenerateIdAndSave() {
        // Arrange
        Room roomToSave = new Room();
        RoomEntity entityToSave = new RoomEntity();
        RoomEntity savedEntity = new RoomEntity();
        savedEntity.setId(123L);
        Room savedRoom = new Room();
        savedRoom.setId(123L);

        when(roomMapper.toEntity(roomToSave)).thenReturn(entityToSave);
        when(roomRepository.save(entityToSave)).thenReturn(savedEntity);
        when(roomMapper.toDomain(savedEntity)).thenReturn(savedRoom);

        // Act
        Room result = roomService.save(roomToSave);

        // Assert
        assertNotNull(result);
        assertEquals(123L, result.getId());
        verify(roomRepository).save(entityToSave);
    }

    @Test
    void deleteById_WhenExists_ShouldDeleteAndReturnTrue() {
        // Arrange
        long id = 1L;
        when(roomRepository.existsById(id)).thenReturn(true);
        doNothing().when(roomRepository).deleteById(id);

        // Act
        Boolean result = roomService.deleteById(id);

        // Assert
        assertTrue(result);
        verify(roomRepository).deleteById(id);
    }

    @Test
    void deleteById_WhenNotExists_ShouldReturnFalse() {
        // Arrange
        long id = 1L;
        when(roomRepository.existsById(id)).thenReturn(false);

        // Act
        Boolean result = roomService.deleteById(id);

        // Assert
        assertFalse(result);
        verify(roomRepository, times(0)).deleteById(id);
    }

    @Test
    void findByHotelId_ShouldReturnRoom() {
        // Arrange
        Long hotelId = 1L;
        RoomEntity entity = new RoomEntity();
        entity.setId(1L);
        Room room = new Room();
        room.setId(1L);

        when(roomRepository.findByHotelId(hotelId)).thenReturn(List.of(entity));
        when(roomMapper.toDomain(entity)).thenReturn(room);

        // Act
        Room result = roomService.findByHotelId(hotelId);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }
}
