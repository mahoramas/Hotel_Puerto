package org.docencia.hotel.web.rest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Set;

import org.docencia.hotel.domain.api.BookingDomain;
import org.docencia.hotel.domain.model.Booking;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(BookingController.class)
class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingDomain bookingDomain;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void findByIdTestReturnBooking() throws Exception {
        long id = 1L;
        Booking booking = new Booking();
        booking.setId(id);

        when(bookingDomain.findById(id)).thenReturn(booking);

        mockMvc.perform(get("/api/bookings/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));
    }

    @Test
    void findByIdTestReturnNotFound() throws Exception {
        long id = 1L;
        when(bookingDomain.findById(id)).thenReturn(null);

        mockMvc.perform(get("/api/bookings/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void saveTest() throws Exception {
        Booking bookingToSave = new Booking();
        Booking savedBooking = new Booking();
        savedBooking.setId(1L);

        when(bookingDomain.save(any(Booking.class))).thenReturn(savedBooking);

        mockMvc.perform(post("/api/bookings/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookingToSave)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void saveTestReturnBadRequest() throws Exception {
        Booking bookingToSave = new Booking();
        when(bookingDomain.save(any(Booking.class))).thenReturn(null);

        mockMvc.perform(post("/api/bookings/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookingToSave)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void findAllTestReturnList() throws Exception {
        Booking booking = new Booking();
        booking.setId(1L);

        when(bookingDomain.findAll()).thenReturn(Set.of(booking));

        mockMvc.perform(get("/api/bookings/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L));
    }

    @Test
    void deleteByIdTestReturnStatus() throws Exception {
        long id = 1L;
        when(bookingDomain.deleteById(id)).thenReturn(true);

        mockMvc.perform(delete("/api/bookings/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.borrada").value(true));
    }
}
