package org.docencia.hotel.persistence.jpa.entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * @author mahoramas
 * @version 1.0.0
 */
@Entity
@Table(name = "hotel")
public class HotelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "hotel")
    private RoomEntity rooms;

    /**
     * Constructor vacio
     */
    public HotelEntity() {
    }

    /**
     * Constructor con el identificador de la clase
     *
     * @param id id del hotel
     */
    public HotelEntity(Long id) {
        this.id = id;
    }

    /**
     * Constructor con los atributos de la clase 
     *
     * @param id id del hotel
     * @param name nombre del hotel
     * @param address direccion del hotel
     * @param rooms habitaciones del hotel
     */
    public HotelEntity(Long id, String name, String address, RoomEntity rooms) {
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

    public RoomEntity getRooms() {
        return this.rooms;
    }

    public void setRooms(RoomEntity rooms) {
        this.rooms = rooms;
    }

    public HotelEntity id(Long id) {
        setId(id);
        return this;
    }

    public HotelEntity name(String name) {
        setName(name);
        return this;
    }

    public HotelEntity address(String address) {
        setAddress(address);
        return this;
    }

    public HotelEntity rooms(RoomEntity rooms) {
        setRooms(rooms);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof HotelEntity)) {
            return false;
        }
        HotelEntity hotelEntity = (HotelEntity) o;
        return Objects.equals(id, hotelEntity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "{"
                + " id='" + getId() + "'"
                + ", name='" + getName() + "'"
                + ", address='" + getAddress() + "'"
                + ", rooms='" + getRooms() + "'"
                + "}";
    }
}
