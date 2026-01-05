package org.docencia.hotel.domain.model;

import java.util.Objects;
import java.util.Set;

/**
 * @author mahoramas
 * @version 1.0.0
 */
public class Room {
    private Long id;

    private String number;

    private String type;

    private float pricePerNight;

    private Hotel hotel;

    private Set<Booking> bookings;

    /**
     * Constructor vacio 
     */
    public Room() {
    }

    /**
     * Constructor con el identificador de la clase 
     * @param id id de la habitacion
     */
    public Room(Long id) {
        this.id = id;
    }
    
    /**
     * Constructor con los atributos de la clase RoomEntity
     * @param id id de la habitacion
     * @param number numero de la habitacion
     * @param type tipo de habitacion
     * @param pricePerNight precio por noche de la habitacion
     * @param hotel hotel donde se encuentra la habitacion
     * @param bookings reservas que tiene la habitacion
     */
    public Room(Long id, String number, String type, float pricePerNight, Hotel hotel, Set<Booking> bookings) {
        this.id = id;
        this.number = number;
        this.type = type;
        this.pricePerNight = pricePerNight;
        this.hotel = hotel;
        this.bookings = bookings;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPricePerNight() {
        return this.pricePerNight;
    }

    public void setPricePerNight(float pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public Hotel getHotel() {
        return this.hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Set<Booking> getBookings() {
        return this.bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Room)) {
            return false;
        }
        Room room = (Room) o;
        return Objects.equals(id, room.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", number='" + getNumber() + "'" +
            ", type='" + getType() + "'" +
            ", pricePerNight='" + getPricePerNight() + "'" +
            ", hotel='" + getHotel() + "'" +
            ", bookings='" + getBookings() + "'" +
            "}";
    }
}
