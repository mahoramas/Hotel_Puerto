package org.docencia.hotel.web.rest;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.docencia.hotel.domain.api.BookingDomain;
import org.docencia.hotel.domain.model.Booking;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
/**
 * @author mahoramas
 * @version 1.0.0
 */
@Tag(name = "Bookings", description = "Operaciones REST de reservas")
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingDomain bookingDomain;

    public BookingController(BookingDomain bookingDomain) {
        this.bookingDomain = bookingDomain;
    }

    @Operation(summary = "Obtener reserva por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "reserva no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Booking> findById(@PathVariable Long id) {
        Booking booking = bookingDomain.findById(id);
        if (booking == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(booking);
    }

    @Operation(summary = "Guardar reserva")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "reserva creada correctamente"),
        @ApiResponse(responseCode = "400", description = "No se pudo guardar la reserva")
    })
    @PostMapping("/add")
    public ResponseEntity<Booking> save(@Valid @RequestBody Booking booking) {
        Booking bookingSaved = bookingDomain.save(booking);
        if (bookingSaved == null) {
            return  ResponseEntity.badRequest().build();
        }
        return  ResponseEntity.ok().body(booking);
    }

    @Operation(summary = "Obtener todos las reservas")
    @GetMapping("/")
    public Set<Booking> findAll() {
        return bookingDomain.findAll();
    }

    @Operation(summary = "Borrar reserva")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "reserva borrada correctamente"),
            @ApiResponse(responseCode = "404", description = "reserva no encontrada")
    })    
    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteById(@PathVariable(value = "id") Long id) {
        boolean respuesta = bookingDomain.deleteById(id);
        Map<String,Boolean> response = new HashMap<>();
        response.put("borrada", respuesta);
        return response;

    }
}
