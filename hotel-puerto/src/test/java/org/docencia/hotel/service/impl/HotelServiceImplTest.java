package org.docencia.hotel.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.docencia.hotel.domain.model.Hotel;
import org.docencia.hotel.mapper.jpa.HotelMapper;
import org.docencia.hotel.persistence.jpa.entity.HotelEntity;
import org.docencia.hotel.persistence.repository.jpa.HotelRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class HotelServiceImplTest {

    @Mock
    private HotelRepository hotelRepository;

    @Mock
    private HotelMapper hotelMapper;

    @InjectMocks
    private HotelServiceImpl hotelService;

    @Test
    void findAll_ShouldReturnSetOfHotels() {
        // Arrange
        HotelEntity entity = new HotelEntity();
        entity.setId(1L);
        entity.setName("Hotel Test");

        Hotel hotel = new Hotel();
        hotel.setId(1L);
        hotel.setName("Hotel Test");

        when(hotelRepository.findAll()).thenReturn(List.of(entity));
        when(hotelMapper.toDomain(anySet())).thenReturn(Set.of(hotel));

        // Act
        Set<Hotel> result = hotelService.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(hotelRepository).findAll();
        verify(hotelMapper).toDomain(any(Set.class));
    }

    @Test
    void findById_WhenExists_ShouldReturnHotel() {
        // Arrange
        long id = 1L;
        HotelEntity entity = new HotelEntity();
        entity.setId(id);

        Hotel hotel = new Hotel();
        hotel.setId(id);

        when(hotelRepository.findById(id)).thenReturn(Optional.of(entity));
        when(hotelMapper.toDomain(entity)).thenReturn(hotel);

        // Act
        Hotel result = hotelService.findById(id);

        // Assert
        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(hotelRepository).findById(id);
    }

    @Test
    void findById_WhenNotExists_ShouldReturnNull() {
        // Arrange
        long id = 1L;
        when(hotelRepository.findById(id)).thenReturn(Optional.empty());
        when(hotelMapper.toDomain((HotelEntity) null)).thenReturn(null);

        // Act
        Hotel result = hotelService.findById(id);

        // Assert
        assertNull(result);
    }

    @Test
    void save_ShouldGenerateIdAndSave() {
        // Arrange
        Hotel hotelToSave = new Hotel();
        hotelToSave.setName("New Hotel");

        HotelEntity entityToSave = new HotelEntity();
        entityToSave.setName("New Hotel");

        HotelEntity savedEntity = new HotelEntity();
        savedEntity.setId(123L);
        savedEntity.setName("New Hotel");

        Hotel savedHotel = new Hotel();
        savedHotel.setId(123L);
        savedHotel.setName("New Hotel");

        when(hotelMapper.toEntity(hotelToSave)).thenReturn(entityToSave);
        when(hotelRepository.save(entityToSave)).thenReturn(savedEntity);
        when(hotelMapper.toDomain(savedEntity)).thenReturn(savedHotel);

        // Act
        Hotel result = hotelService.save(hotelToSave);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals("New Hotel", result.getName());
        verify(hotelRepository).save(entityToSave);
    }

    @Test
    void deleteById_WhenExists_ShouldDeleteAndReturnTrue() {
        // Arrange
        long id = 1L;
        when(hotelRepository.existsById(id)).thenReturn(true);

        // Act
        Boolean result = hotelService.deleteById(id);

        // Assert
        assertTrue(result);
        verify(hotelRepository).deleteById(id);
    }

    @Test
    void deleteById_WhenNotExists_ShouldReturnFalse() {
        // Arrange
        long id = 1L;
        when(hotelRepository.existsById(id)).thenReturn(false);

        // Act
        Boolean result = hotelService.deleteById(id);

        // Assert
        assertFalse(result);
        verify(hotelRepository, times(0)).deleteById(id);
    }
}
