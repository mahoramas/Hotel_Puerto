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

import org.docencia.hotel.domain.model.Guest;
import org.docencia.hotel.domain.model.GuestPreferences;
import org.docencia.hotel.mapper.jpa.GuestMapper;
import org.docencia.hotel.mapper.nosql.GuestPreferencesMapper;
import org.docencia.hotel.persistence.jpa.entity.GuestEntity;
import org.docencia.hotel.persistence.nosql.document.GuestPreferencesDocument;
import org.docencia.hotel.persistence.repository.jpa.GuestJpaRepository;
import org.docencia.hotel.persistence.repository.nosql.GuestPreferencesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GuestServiceImplTest {

    @Mock
    private GuestJpaRepository guestRepository;

    @Mock
    private GuestMapper guestMapper;

    @Mock
    private GuestPreferencesMapper preferencesMapper;

    @Mock
    private GuestPreferencesRepository preferencesRepository;

    @InjectMocks
    private GuestServiceImpl guestService;

    @Test
    void findAll_ShouldReturnSetOfGuests() {
        // Arrange
        GuestEntity entity = new GuestEntity();
        entity.setId(1L);

        Guest guest = new Guest();
        guest.setId(1L);

        when(guestRepository.findAll()).thenReturn(List.of(entity));
        when(guestMapper.toDomain(anySet())).thenReturn(Set.of(guest));

        // Act
        Set<Guest> result = guestService.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(guestRepository).findAll();
    }

    @Test
    void findById_WhenExists_ShouldReturnGuest() {
        // Arrange
        long id = 1L;
        GuestEntity entity = new GuestEntity();
        entity.setId(id);

        Guest guest = new Guest();
        guest.setId(id);

        when(guestRepository.findById(id)).thenReturn(Optional.of(entity));
        when(guestMapper.toDomain(entity)).thenReturn(guest);

        // Act
        Guest result = guestService.findById(id);

        // Assert
        assertNotNull(result);
        assertEquals(id, result.getId());
    }

    @Test
    void findById_WhenNotExists_ShouldReturnNull() {
        // Arrange
        long id = 1L;
        when(guestRepository.findById(id)).thenReturn(Optional.empty());
        when(guestMapper.toDomain((GuestEntity) null)).thenReturn(null);

        // Act
        Guest result = guestService.findById(id);

        // Assert
        assertNull(result);
    }

    @Test
    void save_ShouldGenerateIdAndSave() {
        // Arrange
        Guest guestToSave = new Guest();
        // guestToSave.setId(null); // Explicitly null

        GuestEntity entityToSave = new GuestEntity();

        GuestEntity savedEntity = new GuestEntity();
        savedEntity.setId(123L);

        Guest savedGuest = new Guest();
        savedGuest.setId(123L);

        when(guestMapper.toEntity(guestToSave)).thenReturn(entityToSave);
        when(guestRepository.save(entityToSave)).thenReturn(savedEntity);
        when(guestMapper.toDomain(savedEntity)).thenReturn(savedGuest);

        // Act
        Guest result = guestService.save(guestToSave);

        // Assert
        assertNotNull(result);
        assertEquals(123L, result.getId());
        verify(guestRepository).save(entityToSave);
    }

    @Test
    void deleteById_WhenExists_ShouldDeleteAndReturnTrue() {
        // Arrange
        long id = 1L;
        when(guestRepository.existsById(id)).thenReturn(true);
        doNothing().when(guestRepository).deleteById(id);

        // Act
        Boolean result = guestService.deleteById(id);

        // Assert
        assertTrue(result);
        verify(guestRepository).deleteById(id);
    }

    @Test
    void deleteById_WhenNotExists_ShouldReturnFalse() {
        // Arrange
        long id = 1L;
        when(guestRepository.existsById(id)).thenReturn(false);

        // Act
        Boolean result = guestService.deleteById(id);

        // Assert
        assertFalse(result);
        verify(guestRepository, times(0)).deleteById(id);
    }

    @Test
    void findPreferences_WhenExists_ShouldReturnPreferences() {
        // Arrange
        Long guestId = 1L;
        GuestPreferencesDocument doc = new GuestPreferencesDocument();
        doc.setId(guestId);

        GuestPreferences prefs = new GuestPreferences();
        prefs.setGuestId(guestId);

        when(preferencesRepository.findById(guestId)).thenReturn(Optional.of(doc));
        when(preferencesMapper.toDomain(doc)).thenReturn(prefs);

        // Act
        GuestPreferences result = guestService.findPreferences(guestId);

        // Assert
        assertNotNull(result);
        assertEquals(guestId, result.getGuestId());
    }

    @Test
    void savePreference_ShouldSaveAndReturn() {
        // Arrange
        Long guestId = 1L;
        GuestPreferences prefs = new GuestPreferences();
        prefs.setGuestId(guestId);

        GuestPreferencesDocument doc = new GuestPreferencesDocument();
        doc.setId(guestId);

        when(preferencesMapper.toDocument(prefs)).thenReturn(doc);
        when(preferencesRepository.save(doc)).thenReturn(doc);
        when(preferencesMapper.toDomain(doc)).thenReturn(prefs);

        // Act
        GuestPreferences result = guestService.savePreference(guestId, prefs);

        // Assert
        assertNotNull(result);
        assertEquals(guestId, result.getGuestId());
        verify(preferencesRepository).save(doc);
    }

    @Test
    void deletePreferences_WhenExists_ShouldDelete() {
        // Arrange
        Long guestId = 1L;
        when(preferencesRepository.findById(guestId)).thenReturn(Optional.of(new GuestPreferencesDocument()));
        doNothing().when(preferencesRepository).deleteById(guestId);

        // Act
        Boolean result = guestService.deletePreferences(guestId);

        // Assert
        assertTrue(result);
        verify(preferencesRepository).deleteById(guestId);
    }
}
