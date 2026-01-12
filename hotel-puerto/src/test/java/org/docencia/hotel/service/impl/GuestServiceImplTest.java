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
    void findAllTestReturnGuests() {
        
        GuestEntity entity = new GuestEntity();
        entity.setId(1L);

        Guest guest = new Guest();
        guest.setId(1L);

        when(guestRepository.findAll()).thenReturn(List.of(entity));
        when(guestMapper.toDomain(anySet())).thenReturn(Set.of(guest));

        Set<Guest> result = guestService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(guestRepository).findAll();
    }

    @Test
    void findByIdTestReturnGuest() {
        
        long id = 1L;
        GuestEntity entity = new GuestEntity();
        entity.setId(id);

        Guest guest = new Guest();
        guest.setId(id);

        when(guestRepository.findById(id)).thenReturn(Optional.of(entity));
        when(guestMapper.toDomain(entity)).thenReturn(guest);

        
        Guest result = guestService.findById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
    }

    @Test
    void findByIdTestReturnNull() {
        
        long id = 1L;
        when(guestRepository.findById(id)).thenReturn(Optional.empty());
        when(guestMapper.toDomain((GuestEntity) null)).thenReturn(null);

        
        Guest result = guestService.findById(id);

        assertNull(result);
    }

    @Test
    void saveTest() {
        
        Guest guestToSave = new Guest();

        GuestEntity entityToSave = new GuestEntity();

        GuestEntity savedEntity = new GuestEntity();
        savedEntity.setId(123L);

        Guest savedGuest = new Guest();
        savedGuest.setId(123L);

        when(guestMapper.toEntity(guestToSave)).thenReturn(entityToSave);
        when(guestRepository.save(entityToSave)).thenReturn(savedEntity);
        when(guestMapper.toDomain(savedEntity)).thenReturn(savedGuest);

        
        Guest result = guestService.save(guestToSave);

        assertNotNull(result);
        assertEquals(123L, result.getId());
        verify(guestRepository).save(entityToSave);
    }

    @Test
    void deleteByIdTestReturnTrue() {
        
        long id = 1L;
        when(guestRepository.existsById(id)).thenReturn(true);
        doNothing().when(guestRepository).deleteById(id);

        
        Boolean result = guestService.deleteById(id);

        assertTrue(result);
        verify(guestRepository).deleteById(id);
    }

    @Test
    void deleteByIdTestReturnFalse() {
        
        long id = 1L;
        when(guestRepository.existsById(id)).thenReturn(false);

        
        Boolean result = guestService.deleteById(id);

        assertFalse(result);
        verify(guestRepository, times(0)).deleteById(id);
    }

    @Test
    void findPreferencesTestReturnPreferences() {
        
        Long guestId = 1L;
        GuestPreferencesDocument doc = new GuestPreferencesDocument();
        doc.setId(guestId);

        GuestPreferences prefs = new GuestPreferences();
        prefs.setGuestId(guestId);

        when(preferencesRepository.findById(guestId)).thenReturn(Optional.of(doc));
        when(preferencesMapper.toDomain(doc)).thenReturn(prefs);

        
        GuestPreferences result = guestService.findPreferences(guestId);

        assertNotNull(result);
        assertEquals(guestId, result.getGuestId());
    }

    @Test
    void savePreferenceTest() {
        
        Long guestId = 1L;
        GuestPreferences prefs = new GuestPreferences();
        prefs.setGuestId(guestId);

        GuestPreferencesDocument doc = new GuestPreferencesDocument();
        doc.setId(guestId);

        when(preferencesMapper.toDocument(prefs)).thenReturn(doc);
        when(preferencesRepository.save(doc)).thenReturn(doc);
        when(preferencesMapper.toDomain(doc)).thenReturn(prefs);

        
        GuestPreferences result = guestService.savePreference(guestId, prefs);

        assertNotNull(result);
        assertEquals(guestId, result.getGuestId());
        verify(preferencesRepository).save(doc);
    }

    @Test
    void deletePreferencesTest() {
        
        Long guestId = 1L;
        when(preferencesRepository.findById(guestId)).thenReturn(Optional.of(new GuestPreferencesDocument()));
        doNothing().when(preferencesRepository).deleteById(guestId);

        
        Boolean result = guestService.deletePreferences(guestId);

        assertTrue(result);
        verify(preferencesRepository).deleteById(guestId);
    }
}
