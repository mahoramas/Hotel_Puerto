package org.docencia.hotel.web.rest;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.docencia.hotel.domain.api.HotelDomain;
import org.docencia.hotel.domain.model.Hotel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/**
 * @author mahoramas
 * @version 1.0.0
 */
@Tag(name = "Hotels", description = "Operaciones REST de hoteles")
@RestController
@RequestMapping("/api/hotels")
public class HotelController {
    private final HotelDomain hotelDomain;

    public HotelController( HotelDomain hotelDomain) {
        this.hotelDomain = hotelDomain;
    }

    @Operation(summary = "Obtener hotel por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "hotel no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Hotel> findById(@PathVariable Long id) {
        Hotel hotel = hotelDomain.findById(id);
        if (hotel == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(hotel);
    }

    @Operation(summary = "Guardar hotel")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "hotel creado correctamente"),
        @ApiResponse(responseCode = "400", description = "No se pudo guardar el hotel")
    })
    @PostMapping("/add")
    public ResponseEntity<Hotel> save(@Valid @RequestBody Hotel hotel) {
        Hotel hotelSaved = hotelDomain.save(hotel);
        if (hotelSaved == null) {
            return  ResponseEntity.badRequest().build();
        }
        return  ResponseEntity.ok().body(hotel);
    }

    @Operation(summary = "Obtener todos los hoteles")
    @GetMapping("/")
    public Set<Hotel> findAll() {
        return hotelDomain.findAll();
    }

    @Operation(summary = "Borrar hotel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "hotel borrado correctamente"),
            @ApiResponse(responseCode = "404", description = "hotel no encontrado")
    })    
    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteById(@PathVariable(value = "id") Long id) {
        boolean respuesta = hotelDomain.deleteById(id);
        Map<String,Boolean> response = new HashMap<>();
        response.put("borrada", respuesta);
        return response;

    }
}
