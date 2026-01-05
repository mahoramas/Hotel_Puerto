package org.docencia.hotel.domain.model;

import java.time.LocalDate;

/**
 * @author mahoramas
 * @version 1.0.0
 */
public class Booking {
    private Long id;

    private Room room;

    private Guest guest;

    private LocalDate checkIn;

    private LocalDate checkOut;

    /**
     * Constructor vacio
     */
    public Booking() {
    }

    /**
     * Constructor con identificador
     * @param id id de la reserva
     */
    public Booking(Long id) {
        this.id = id;
    }

    /**
     * Constructor con todos los parametros de la clase
     * @param id identificador de la reserva
     * @param room identificador de la habitacion de la reserva
     * @param guest identificador de huesped de la reserva
     * @param checkIn fecha y hora de entrada de la reserva
     * @param checkOut fecha y hora de salida de la reserva
     */
    public Booking(Long id, Room room, Guest guest, LocalDate checkIn, LocalDate checkOut) {
        this.id = id;
        this.room = room;
        this.guest = guest;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Booking other = (Booking) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    

}
