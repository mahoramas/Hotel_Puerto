package org.docencia.hotel.service.api;

import java.util.Set;

import org.docencia.hotel.domain.model.Hotel;
/**
 * @author mahoramas
 * @version 1.0.0
 */
public interface HotelService {
    
    /**
     * Metodo que devulve una lista de todos los hoteles
     * @return Lista de hoteles
     */
    Set<Hotel> findall();

    /**
     * Metodo que devuelve un hotel por su identificador
     * @param id id del hotel
     * @return hotel con ese identificador, null si no existe
     */
    Hotel findById(long id);

    /**
     * Metodo que guarda un hotel
     * @param hotel hotel que se quiere guardar
     * @return hotel guardado
     */
    Hotel save(Hotel hotel);

    /**
     * Metodo que elimina un hotel por su id
     * @param id id del hotel que se quiere eliminar
     * @return true si se ha eliminado y false si no se ha eliminado
     */
    Boolean deleteById(long id);
}
