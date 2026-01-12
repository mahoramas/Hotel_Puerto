package org.docencia.hotel.persistence.nosql.document;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class GuestPreferencesDocumentTest {

    @Test
    void testConstructorsAndAccessors() {
        GuestPreferencesDocument doc = new GuestPreferencesDocument();
        doc.setId(1L);
        doc.setPreferredLanguage("French");
        doc.setNewsLetterOptIn(false);
        List<String> tags = new ArrayList<>();
        doc.setTags(tags);
        doc.setFavoriteRoomType("Penthouse");
        doc.setNotes("VIP");

        assertEquals(1L, doc.getId());
        assertEquals("French", doc.getPreferredLanguage());
        assertEquals(false, doc.isNewsLetterOptIn());
        assertEquals(tags, doc.getTags());
        assertEquals("Penthouse", doc.getFavoriteRoomType());
        assertEquals("VIP", doc.getNotes());

        GuestPreferencesDocument doc2 = new GuestPreferencesDocument(1L, "French", false, tags, "Penthouse", "VIP");
        assertEquals(doc, doc2);

        GuestPreferencesDocument doc3 = new GuestPreferencesDocument(1L);
        assertEquals(1L, doc3.getId());
    }
}
