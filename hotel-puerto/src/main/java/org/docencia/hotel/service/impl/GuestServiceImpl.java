package org.docencia.hotel.service.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.docencia.hotel.domain.model.Guest;
import org.docencia.hotel.domain.model.GuestPreferences;
import org.docencia.hotel.mapper.jpa.GuestMapper;
import org.docencia.hotel.mapper.nosql.GuestPreferencesMapper;
import org.docencia.hotel.persistence.repository.jpa.GuestJpaRepository;
import org.docencia.hotel.persistence.repository.nosql.GuestPreferencesRepository;
import org.docencia.hotel.service.api.GuestService;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
/**
 * @author mahoramas
 * @version 1.0.0
 */
@Service
public class GuestServiceImpl implements GuestService {

    private final GuestJpaRepository guestRepository;
    private final GuestMapper guestMapper;

    private final GuestPreferencesMapper preferencesMapper;
    private final GuestPreferencesRepository preferencesRepository;

    public GuestServiceImpl(GuestMapper guestMapper, GuestJpaRepository guestRepository, GuestPreferencesMapper preferencesMapper, GuestPreferencesRepository preferencesRepository) {
        this.guestMapper = guestMapper;
        this.guestRepository = guestRepository;
        this.preferencesMapper = preferencesMapper;
        this.preferencesRepository = preferencesRepository;
    }


    
    @Override
    public Set<Guest> findall() {
        return guestMapper.toDomain(new HashSet<>(guestRepository.findAll()));
    }

    @Override
    public Guest findById(long id) {
        return guestMapper.toDomain(guestRepository.findById(id).orElse(null));
    }

    @Override
    @Transactional
    public Guest save(Guest guest) {
        if (guest.getId() == null) {
            guest.setId(UUID.randomUUID().getMostSignificantBits());
        }
        return guestMapper.toDomain(guestRepository.save(guestMapper.toEntity(guest)));
    }

    @Override
    @Transactional
    public Boolean deleteById(long id) {
        if (guestRepository.findById(id) == null) {
            return false;
        }
        guestRepository.deleteById(id);
        return true;
    }

    @Override
    public GuestPreferences findPreferences(Long guestId) {
        return preferencesMapper.toDomain(preferencesRepository.findById(guestId).orElse(null));
    }

    @Override
    @Transactional
    public GuestPreferences savePreference(Long guestId, GuestPreferences preferences) {
        if (guestId == null) {
            preferences.setGuestId(UUID.randomUUID().getMostSignificantBits());
        } else {
            preferences.setGuestId(guestId);
        }
        return preferencesMapper.toDomain(preferencesRepository.save(preferencesMapper.toDocument(preferences)));
    }

    @Override
    @Transactional
    public Boolean deletePreferences(Long guestId) {
        if (preferencesRepository.findById(guestId) == null) {
            return false;
        }
        preferencesRepository.deleteById(guestId);
        return true;
    }
    
}
