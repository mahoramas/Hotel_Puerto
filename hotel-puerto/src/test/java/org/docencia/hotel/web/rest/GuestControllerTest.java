package org.docencia.hotel.web.rest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Set;

import org.docencia.hotel.domain.api.GuestDomain;
import org.docencia.hotel.domain.model.Guest;
import org.docencia.hotel.domain.model.GuestPreferences;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(GuestController.class)
class GuestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GuestDomain guestDomain;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void findByIdTestReturnGuest() throws Exception {
        long id = 1L;
        Guest guest = new Guest();
        guest.setId(id);
        guest.setFullName("Test Guest");

        when(guestDomain.findById(id)).thenReturn(guest);

        mockMvc.perform(get("/api/guests/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.fullName").value("Test Guest"));
    }

    @Test
    void findByIdTestReturnNotFound() throws Exception {
        long id = 1L;
        when(guestDomain.findById(id)).thenReturn(null);

        mockMvc.perform(get("/api/guests/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void saveTestReturnSavedGuest() throws Exception {
        Guest guestToSave = new Guest();
        guestToSave.setFullName("New Guest");

        Guest savedGuest = new Guest();
        savedGuest.setId(1L);
        savedGuest.setFullName("New Guest");

        when(guestDomain.save(any(Guest.class))).thenReturn(savedGuest);

        mockMvc.perform(post("/api/guests/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(guestToSave)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.fullName").value("New Guest"));
    }

    @Test
    void saveTestReturnBadRequest() throws Exception {
        Guest guestToSave = new Guest();
        when(guestDomain.save(any(Guest.class))).thenReturn(null);

        mockMvc.perform(post("/api/guests/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(guestToSave)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void findAllTestReturnList() throws Exception {
        Guest guest = new Guest();
        guest.setId(1L);
        guest.setFullName("Test Guest");

        when(guestDomain.findAll()).thenReturn(Set.of(guest));

        mockMvc.perform(get("/api/guests/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L));
    }

    @Test
    void deleteByIdTestReturnStatus() throws Exception {
        long id = 1L;
        when(guestDomain.deleteById(id)).thenReturn(true);

        mockMvc.perform(delete("/api/guests/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"borrado\":true}"));
    }

    @Test
    void savePreferencesTest() throws Exception {
        Long guestId = 1L;
        GuestPreferences prefs = new GuestPreferences();
        prefs.setGuestId(guestId);

        Guest guest = new Guest();
        guest.setId(guestId);

        when(guestDomain.findById(guestId)).thenReturn(guest);
        when(guestDomain.savePreference(eq(guestId), any(GuestPreferences.class))).thenReturn(prefs);

        mockMvc.perform(post("/api/guests/add/preferences/{guestId}", guestId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(prefs)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.guestId").value(guestId));
    }

    @Test
    void savePreferencesTestReturnNotFound() throws Exception {
        Long guestId = 1L;
        GuestPreferences prefs = new GuestPreferences();

        when(guestDomain.findById(guestId)).thenReturn(null);

        mockMvc.perform(post("/api/guests/add/preferences/{guestId}", guestId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(prefs)))
                .andExpect(status().isNotFound());
    }

    @Test
    void findPreferenceByIdTestReturnPreferences() throws Exception {
        Long guestId = 1L;
        GuestPreferences prefs = new GuestPreferences();
        prefs.setGuestId(guestId);

        when(guestDomain.findPreferences(guestId)).thenReturn(prefs);

        mockMvc.perform(get("/api/guests/preferences/{guestId}", guestId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.guestId").value(guestId));
    }

    @Test
    void findPreferenceByIdTestReturnNotFound() throws Exception {
        Long guestId = 1L;
        when(guestDomain.findPreferences(guestId)).thenReturn(null);

        mockMvc.perform(get("/api/guests/preferences/{guestId}", guestId))
                .andExpect(status().isNotFound());
    }

    @Test
    void deletePreferenceByIdTestReturnStatus() throws Exception {
        Long guestId = 1L;
        when(guestDomain.deletePreferences(guestId)).thenReturn(true);

        mockMvc.perform(delete("/api/guests/preferences/{guestId}", guestId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.borrado").value(true));
    }

}
