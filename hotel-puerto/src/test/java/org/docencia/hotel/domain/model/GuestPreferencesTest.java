package org.docencia.hotel.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class GuestPreferencesTest {

    @Test
    void testConstructorsAndAccessors() {
        GuestPreferences prefs = new GuestPreferences();
        prefs.setGuestId(1L);
        prefs.setPreferredLanguage("English");
        prefs.setNewsLetterOptIn(true);
        List<String> tags = new ArrayList<>();
        prefs.setTags(tags);
        prefs.setFavoriteRoomType("Suite");
        prefs.setNotes("Note");

        assertEquals(1L, prefs.getGuestId());
        assertEquals("English", prefs.getPreferredLanguage());
        assertTrue(prefs.getNewsLetterOptIn());
        assertEquals(tags, prefs.getTags());
        assertEquals("Suite", prefs.getFavoriteRoomType());
        assertEquals("Note", prefs.getNotes());

        GuestPreferences prefs2 = new GuestPreferences(1L, "English", true, tags, "Suite", "Note");
        assertEquals(prefs, prefs2);

        GuestPreferences prefs3 = new GuestPreferences(1L);
        assertEquals(1L, prefs3.getGuestId());
    }
}
