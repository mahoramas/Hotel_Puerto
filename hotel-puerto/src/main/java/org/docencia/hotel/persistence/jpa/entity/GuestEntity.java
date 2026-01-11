package org.docencia.hotel.persistence.jpa.entity;

import java.util.Objects;
import java.util.Set;

import org.docencia.hotel.persistence.nosql.document.GuestPreferencesDocument;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

/**
 * @author mahoramas
 * @version 1.0.0
 */
@Entity
@Table(name = "guest")
public class GuestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @OneToMany(mappedBy = "guestId")
    Set<BookingEntity> bookings;

    @Transient
    private GuestPreferencesDocument preference;

    /**
     * Constructor vacio
     */
    public GuestEntity() {
    }

    /**
     * Constructor con identificador de la clase
     *
     * @param id id del huesped
     */
    public GuestEntity(Long id) {
        this.id = id;
    }

    /**
     * Constructor con todos los parametros de la clase
     *
     * @param id id del huesped
     * @param fullName nombre completo del huesped
     * @param email email del huesped
     * @param phone telefono del huesped
     * @param bookings reservas del huesped
     * @param preference preferencias del huesped
     */
    public GuestEntity(Set<BookingEntity> bookings, String email, String fullName, Long id, String phone,
            GuestPreferencesDocument preference) {
        this.bookings = bookings;
        this.email = email;
        this.fullName = fullName;
        this.id = id;
        this.phone = phone;
        this.preference = preference;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<BookingEntity> getBookings() {
        return bookings;
    }

    public void setBookings(Set<BookingEntity> bookings) {
        this.bookings = bookings;
    }

    public GuestPreferencesDocument getPreference() {
        return preference;
    }

    public void setPreference(GuestPreferencesDocument preference) {
        this.preference = preference;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.id);
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
        final GuestEntity other = (GuestEntity) obj;
        return Objects.equals(this.id, other.id);
    }

}
