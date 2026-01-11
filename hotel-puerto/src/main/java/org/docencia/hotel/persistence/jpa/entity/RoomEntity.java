package org.docencia.hotel.persistence.jpa.entity;

import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * @author mahoramas
 * @version 1.0.0
 */
@Entity
@Table(name = "room")
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "number")
    private String number;

    @Column(name = "type")
    private String type;

    @Column(name = "price_per_night")
    private float pricePerNight;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private HotelEntity hotel;

    @OneToMany(mappedBy = "roomId")
    Set<BookingEntity> bookings;

    /**
     * Constructor vacio de la clase
     */
    public RoomEntity() {
    }

    /**
     * Constructor con el identificador de la clase
     *
     * @param id id de la habitacion
     */
    public RoomEntity(Long id) {
        this.id = id;
    }

    /**
     * Constructor con los atributos de la clase RoomEntity
     *
     * @param id id de la habitacion
     * @param number numero de la habitacion
     * @param type tipo de habitacion
     * @param pricePerNight precio por noche de la habitacion
     * @param hotel hotel donde se encuentra la habitacion
     * @param bookings reservas que tiene la habitacion
     */
    public RoomEntity(Long id, String number, String type, float pricePerNight, HotelEntity hotel,
            Set<BookingEntity> bookings) {
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

    public HotelEntity getHotel() {
        return this.hotel;
    }

    public void setHotel(HotelEntity hotel) {
        this.hotel = hotel;
    }

    public Set<BookingEntity> getBookings() {
        return this.bookings;
    }

    public void setBookings(Set<BookingEntity> bookings) {
        this.bookings = bookings;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof RoomEntity)) {
            return false;
        }
        RoomEntity roomEntity = (RoomEntity) o;
        return Objects.equals(id, roomEntity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "{"
                + " id='" + getId() + "'"
                + ", number='" + getNumber() + "'"
                + ", type='" + getType() + "'"
                + ", pricePerNight='" + getPricePerNight() + "'"
                + ", hotel='" + getHotel() + "'"
                + ", bookings='" + getBookings() + "'"
                + "}";
    }
}
