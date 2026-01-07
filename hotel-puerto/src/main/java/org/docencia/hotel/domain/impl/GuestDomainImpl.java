package org.docencia.hotel.domain.impl;

import java.util.Set;

import org.docencia.hotel.domain.api.GuestDomain;
import org.docencia.hotel.domain.model.Guest;
import org.docencia.hotel.domain.model.GuestPreferences;
import org.docencia.hotel.service.api.GuestService;
import org.springframework.stereotype.Service;

@Service
public class GuestDomainImpl implements GuestDomain {

    private final GuestService service;

    public GuestDomainImpl(GuestService service) {
        this.service = service;
    }

    @Override
    public Set<Guest> findall() {
        return service.findall();
    }

    @Override
    public Guest findById(long id) {
        return service.findById(id);
    }

    @Override
    public Guest save(Guest guest) {
        return service.save(guest);
    }

    @Override
    public Boolean deleteById(long id) {
        return service.deleteById(id);
    }

    @Override
    public GuestPreferences findPreferences(Long guestId) {
        return service.findPreferences(guestId);
    }

    @Override
    public GuestPreferences savePreference(Long guestId, GuestPreferences preferences) {
        return service.savePreference(guestId, preferences);
    }

    @Override
    public Boolean deletePreferences(Long guestId) {
        return service.deletePreferences(guestId);
    }
}
