package org.docencia.hotel.service.api;

import java.util.Set;

import org.docencia.hotel.domain.model.Room;
/**
 * @author mahoramas
 * @version 1.0.0
 */
public interface RoomService {

    /**
     * Metodo que devulve una lista de todas las habitaciones
     * @return Lista de habitaciones
     */
    Set<Room> findAll();

    /**
     * Metodo que devuelve una habitacion por su identificador
     * @param id id de la habitacion
     * @return la habitacion con ese identificador, null si no existe
     */
    Room findById(long id);

    /**
     * Metodo que guarda una habitacion
     * @param room habitacion que se quiere guardar
     * @return habitacion guardada
     */
    Room save(Room room);

    /**
     * Metodo que elimina una habitacion por su id
     * @param id id de la habitacion que se quiere eliminar
     * @return true si se ha eliminado y false si no se ha eliminado
     */
    Boolean deleteById(long id);

    /**
     * Metodo que devuelve una habitacion por el id de hotel
     * @param hotelId id del hotel donde pertenece la habitacion
     * @return habitacion que este en el hotel, null si no existe
     */
    Room findByHotelId(Long hotelId);
}
