package org.docencia.hotel.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.docencia.hotel.domain.model.Hotel;
import org.docencia.hotel.mapper.jpa.HotelMapper;
import org.docencia.hotel.persistence.jpa.entity.HotelEntity;
import org.docencia.hotel.persistence.repository.jpa.HotelRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anySet;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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
    void findAllTestReturnHotels() {
        HotelEntity entity = new HotelEntity();
        entity.setId(1L);
        entity.setName("Hotel Test");

        Hotel hotel = new Hotel();
        hotel.setId(1L);
        hotel.setName("Hotel Test");

        when(hotelRepository.findAll()).thenReturn(List.of(entity));
        when(hotelMapper.toDomain(anySet())).thenReturn(Set.of(hotel));

        Set<Hotel> result = hotelService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(hotelRepository).findAll();
        verify(hotelMapper).toDomain(any(Set.class));
    }

    @Test
    void findByIdTestReturnHotel() {
        long id = 1L;
        HotelEntity entity = new HotelEntity();
        entity.setId(id);

        Hotel hotel = new Hotel();
        hotel.setId(id);

        when(hotelRepository.findById(id)).thenReturn(Optional.of(entity));
        when(hotelMapper.toDomain(entity)).thenReturn(hotel);

        Hotel result = hotelService.findById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(hotelRepository).findById(id);
    }

    @Test
    void findByIdTestReturnNull() {
        long id = 1L;
        when(hotelRepository.findById(id)).thenReturn(Optional.empty());
        when(hotelMapper.toDomain((HotelEntity) null)).thenReturn(null);

        Hotel result = hotelService.findById(id);

        assertNull(result);
    }

    @Test
    void saveTest() {
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

        Hotel result = hotelService.save(hotelToSave);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals("New Hotel", result.getName());
        verify(hotelRepository).save(entityToSave);
    }

    @Test
    void deleteByIdTestReturnTrue() {
        long id = 1L;
        when(hotelRepository.existsById(id)).thenReturn(true);

        Boolean result = hotelService.deleteById(id);

        assertTrue(result);
        verify(hotelRepository).deleteById(id);
    }

    @Test
    void deleteByIdTestFalse() {
        long id = 1L;
        when(hotelRepository.existsById(id)).thenReturn(false);

        Boolean result = hotelService.deleteById(id);

        assertFalse(result);
        verify(hotelRepository, times(0)).deleteById(id);
    }
}
