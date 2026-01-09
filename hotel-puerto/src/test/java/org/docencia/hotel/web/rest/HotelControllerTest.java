package org.docencia.hotel.web.rest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Set;

import org.docencia.hotel.domain.api.HotelDomain;
import org.docencia.hotel.domain.model.Hotel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(HotelController.class)
class HotelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HotelDomain hotelDomain;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void findById_WhenExists_ShouldReturnHotel() throws Exception {
        long id = 1L;
        Hotel hotel = new Hotel();
        hotel.setId(id);
        hotel.setName("Test Hotel");

        when(hotelDomain.findById(id)).thenReturn(hotel);

        mockMvc.perform(get("/api/hotels/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value("Test Hotel"));
    }

    @Test
    void findById_WhenNotExists_ShouldReturnNotFound() throws Exception {
        long id = 1L;
        when(hotelDomain.findById(id)).thenReturn(null);

        mockMvc.perform(get("/api/hotels/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void save_ShouldReturnSavedHotel() throws Exception {
        Hotel hotelToSave = new Hotel();
        hotelToSave.setName("New Hotel");

        Hotel savedHotel = new Hotel();
        savedHotel.setId(1L);
        savedHotel.setName("New Hotel");

        when(hotelDomain.save(any(Hotel.class))).thenReturn(savedHotel);

        mockMvc.perform(post("/api/hotels/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(hotelToSave)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("New Hotel"));
    }

    @Test
    void save_WhenServiceReturnsNull_ShouldReturnBadRequest() throws Exception {
        Hotel hotelToSave = new Hotel();
        hotelToSave.setName("New Hotel");

        when(hotelDomain.save(any(Hotel.class))).thenReturn(null);

        mockMvc.perform(post("/api/hotels/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(hotelToSave)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void findAll_ShouldReturnList() throws Exception {
        Hotel hotel = new Hotel();
        hotel.setId(1L);
        hotel.setName("Test Hotel");

        when(hotelDomain.findAll()).thenReturn(Set.of(hotel));

        mockMvc.perform(get("/api/hotels/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Test Hotel"));
    }

    @Test
    void deleteById_ShouldReturnStatus() throws Exception {
        long id = 1L;
        when(hotelDomain.deleteById(id)).thenReturn(true);

        mockMvc.perform(delete("/api/hotels/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.borrada").value(true));
    }
}
