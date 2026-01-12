package org.docencia.hotel.web.rest;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.docencia.hotel.domain.api.GuestDomain;
import org.docencia.hotel.domain.model.Guest;
import org.docencia.hotel.domain.model.GuestPreferences;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mahoramas
 * @version 1.0.0
 */
@Tag(name = "Guests", description = "Operaciones REST de huéspedes")
@RestController
@RequestMapping("/api/guests")
public class GuestController {

    private final GuestDomain guestDomain;

    public GuestController(GuestDomain guestDomain) {
        this.guestDomain = guestDomain;
    }

    @Operation(summary = "Obtener huésped por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Huesped no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Guest> findById(@PathVariable Long id) {
        Guest guest = guestDomain.findById(id);
        if (guest == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(guest);
    }

    @Operation(summary = "Guardar huésped")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Huesped creado correctamente"),
            @ApiResponse(responseCode = "400", description = "No se pudo guardar el huesped")
    })
    @PostMapping("/add")
    public ResponseEntity<Guest> save(@Valid @RequestBody Guest guest) {
        Guest guestSaved = guestDomain.save(guest);
        if (guestSaved == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(guestSaved);
    }

    @Operation(summary = "Obtener todos los huespedes")
    @GetMapping("/")
    public Set<Guest> findAll() {
        return guestDomain.findAll();
    }

    @Operation(summary = "Borrar huesped")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "huesped borrado correctamente"),
            @ApiResponse(responseCode = "404", description = "huesped no encontrado")
    })
    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteById(@PathVariable(value = "id") Long id) {
        boolean respuesta = guestDomain.deleteById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("borrado", respuesta);
        return response;

    }

    @Operation(summary = "Guardar preferencias del huésped")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "preferencias creadas correctamente"),
            @ApiResponse(responseCode = "400", description = "No se pudo guardar las preferencias"),
            @ApiResponse(responseCode = "404", description = "No existe el huesped para guardar las preferencias")

    })
    @PostMapping("/add/preferences/{guestId}")
    public ResponseEntity<GuestPreferences> savePreferences(@PathVariable Long guestId,
            @Valid @RequestBody GuestPreferences preferences) {
        boolean existe = false;
        if (guestDomain.findById(guestId) != null) {
            existe = true;
        }
        if (!existe) {
            return ResponseEntity.notFound().build();
        }
        GuestPreferences preferencesSaved = guestDomain.savePreference(guestId, preferences);
        if (preferencesSaved == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(preferencesSaved);
    }

    @Operation(summary = "Borrar preferencias del huésped")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "preferencias borradas correctamente"),
            @ApiResponse(responseCode = "404", description = "preferencias no encontradas")
    })
    @DeleteMapping("/preferences/{guestId}")
    public Map<String, Boolean> deletePreferenceById(@PathVariable Long guestId) {
        boolean respuesta = guestDomain.deletePreferences(guestId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("borrado", respuesta);
        return response;
    }

    @Operation(summary = "Obtener preferencias del huésped por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "preferencias no encontradas")
    })
    @GetMapping("/preferences/{guestId}")
    public ResponseEntity<GuestPreferences> findPreferenceById(@PathVariable Long guestId) {
        GuestPreferences preferences = guestDomain.findPreferences(guestId);
        if (preferences == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(preferences);
    }
}
