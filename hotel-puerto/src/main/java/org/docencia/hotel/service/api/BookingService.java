package org.docencia.hotel.service.api;

import java.util.Set;

import org.docencia.hotel.domain.model.Booking;
/**
 * @author mahoramas
 * @version 1.0.0
 */
public interface BookingService {

    /**
     * Metodo que devulve una lista de todas las reservas
     * @return Lista de reservas
     */
    Set<Booking> findAll();

    /**
     * Metodo que devuelve una reserva por su identificador
     * @param id id de la reserva
     * @return la reserva con ese identificador, null si no existe
     */
    Booking findById(long id);

    /**
     * Metodo que guarda una reserva
     * @param booking reserva que se quiere guardar
     * @return reserva guardada
     */
    Booking save(Booking booking);

    /**
     * Metodo que elimina una reserva por su id
     * @param id id de la reserva que se quiere eliminar
     * @return true si se ha eliminado y false si no se ha eliminado
     */
    Boolean deleteById(long id);

}
