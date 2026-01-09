package org.docencia.hotel.domain.api;

import java.util.Set;

import org.docencia.hotel.domain.model.Guest;
import org.docencia.hotel.domain.model.GuestPreferences;

/**
 * @author mahoramas
 * @version 1.0.0
 */
public interface GuestDomain {
        /**
     * Metodo que devulve una lista de todos los huespedes
     * @return Lista de huespedes
     */
    Set<Guest> findAll();

    /**
     * Metodo que devuelve un huesped por su identificador
     * @param id id del huesped
     * @return huesped con ese identificador, null si no existe
     */
    Guest findById(long id);

    /**
     * Metodo que guarda un huesped
     * @param guest huesped que se quiere guardar
     * @return huesped guardado
     */
    Guest save(Guest guest);

    /**
     * Metodo que elimina un huesped por su id
     * @param id id del huesped que se quiere eliminar
     * @return true si se ha eliminado y false si no se ha eliminado
     */
    Boolean deleteById(long id);

    /**
     * Metodo que devuelve las preferencias de un huesped por su id
     * @param guestId id del huesped
     * @return preferencias del huesped
     */
    GuestPreferences findPreferences(Long guestId);

    /**
     * Metodo que guarda las preferencias de un huesped por su identificador
     * @param guestId id del huesped
     * @param preferences preferencias del huespred
     * @return preferencias guardadas
     */
    GuestPreferences savePreference(Long guestId, GuestPreferences preferences);

    /**
     * Metodo que elimina las preferencias de un huesped por su id
     * @param guestId id del huesped
     * @return preferencias del huesped
     */
    Boolean deletePreferences(Long guestId);
}
