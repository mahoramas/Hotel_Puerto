package org.docencia.hotel.persistence.jpa.entity;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * @author mahoramas
 * @version 1.0.0
 */
@Entity
@Table(name = "booking")
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private RoomEntity roomId;

    @ManyToOne
    @JoinColumn(name = "guest_id")
    private GuestEntity guestId;

    @Column(name = "check_in")
    private LocalDate checkIn;

    @Column(name = "check_out")
    private LocalDate checkOut;

    /**
     * Constructor vacio
     */
    public BookingEntity() {
    }

    /**
     * Constructor con identificador
     *
     * @param id id de la reserva
     */
    public BookingEntity(Long id) {
        this.id = id;
    }

    /**
     * Constructor con todos los parametros de la clase
     *
     * @param id identificador de la reserva
     * @param roomId identificador de la habitacion de la reserva
     * @param guestId identificador de huesped de la reserva
     * @param checkIn fecha y hora de entrada de la reserva
     * @param checkOut fecha y hora de salida de la reserva
     */
    public BookingEntity(LocalDate checkIn, LocalDate checkOut, GuestEntity guestId, Long id, RoomEntity roomId) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.guestId = guestId;
        this.id = id;
        this.roomId = roomId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoomEntity getRoomId() {
        return roomId;
    }

    public void setRoomId(RoomEntity roomId) {
        this.roomId = roomId;
    }

    public GuestEntity getGuestId() {
        return guestId;
    }

    public void setGuestId(GuestEntity guestId) {
        this.guestId = guestId;
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
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BookingEntity other = (BookingEntity) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BookingEntity{");
        sb.append("id=").append(id);
        sb.append(", room=").append(roomId);
        sb.append(", guest=").append(guestId);
        sb.append(", checkIn=").append(checkIn);
        sb.append(", checkOut=").append(checkOut);
        sb.append('}');
        return sb.toString();
    }
}
