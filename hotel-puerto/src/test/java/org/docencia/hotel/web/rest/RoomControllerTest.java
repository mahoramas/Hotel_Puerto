package org.docencia.hotel.web.rest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Set;

import org.docencia.hotel.domain.api.RoomDomain;
import org.docencia.hotel.domain.model.Room;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(RoomController.class)
class RoomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoomDomain roomDomain;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void findByIdTestReturnRoom() throws Exception {
        long id = 1L;
        Room room = new Room();
        room.setId(id);
        room.setNumber("101");

        when(roomDomain.findById(id)).thenReturn(room);

        mockMvc.perform(get("/api/rooms/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.number").value("101"));
    }

    @Test
    void findByIdTestReturnNotFound() throws Exception {
        long id = 1L;
        when(roomDomain.findById(id)).thenReturn(null);

        mockMvc.perform(get("/api/rooms/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void saveTestReturnSavedRoom() throws Exception {
        Room roomToSave = new Room();
        roomToSave.setNumber("102");

        Room savedRoom = new Room();
        savedRoom.setId(1L);
        savedRoom.setNumber("102");

        when(roomDomain.save(any(Room.class))).thenReturn(savedRoom);

        mockMvc.perform(post("/api/rooms/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(roomToSave)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.number").value("102"));
    }

    @Test
    void saveTestReturnBadRequest() throws Exception {
        Room roomToSave = new Room();
        when(roomDomain.save(any(Room.class))).thenReturn(null);

        mockMvc.perform(post("/api/rooms/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(roomToSave)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void findAllTestReturnList() throws Exception {
        Room room = new Room();
        room.setId(1L);
        room.setNumber("101");

        when(roomDomain.findAll()).thenReturn(Set.of(room));

        mockMvc.perform(get("/api/rooms/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].number").value("101"));
    }

    @Test
    void deleteByIdTestReturnStatus() throws Exception {
        long id = 1L;
        when(roomDomain.deleteById(id)).thenReturn(true);

        mockMvc.perform(delete("/api/rooms/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.borrada").value(true));
    }
}
