package org.docencia.hotel.domain.model;

import java.util.Objects;
import java.util.Set;

/**
 * @author mahoramas
 * @version 1.0.0
 */
public class Hotel {
    private Long id;

    private String name;

    private String address;

    private Set<Room> rooms;

    /**
     * Constructor vacio de la clase Hotel
     */
    public Hotel() {
    }

    /**
     * Constructor con el identificador de la clase
     * @param id id del hotel
     */
    public Hotel(Long id) {
        this.id = id;
    }

    /**
     * Constructor con los atributos de la clase 
     * @param id id del hotel
     * @param name nombre del hotel
     * @param address direccion del hotel
     * @param rooms habitaciones del hotel
     */
    public Hotel(Long id, String name, String address, Set<Room> rooms) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.rooms = rooms;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Room> getRooms() {
        return this.rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Hotel)) {
            return false;
        }
        Hotel hotel = (Hotel) o;
        return Objects.equals(id, hotel.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", address='" + getAddress() + "'" +
            ", rooms='" + getRooms() + "'" +
            "}";
    }
}
