package org.docencia.hotel.domain.model;

import java.util.Objects;
import java.util.Set;

/**
 * @author mahoramas
 * @version 1.0.0
 */
public class Guest {
    private Long id;

    private String fullName;

    private String email;

    private String phone;

    private Set<Booking> bookings;

    private GuestPreferences preference;

    /**
     * Constructor vacio
     */
    public Guest() {
    }

    /**
     * Constructor con identificador de la clase
     * @param id id del huesped
     */
    public Guest(Long id) {
        this.id = id;
    }

    /**
     * Constructor con todos los parametros de la clase
     * @param id id del huesped
     * @param fullName nombre completo del huesped
     * @param email email del huesped
     * @param phone telefono del huesped
     * @param bookings reservas del huesped
     * @param preference preferencias del huesped
     */
    public Guest(Long id, String fullName, String email, String phone, Set<Booking> bookings, GuestPreferences preference) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.bookings = bookings;
        this.preference = preference;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Booking> getBookings() {
        return this.bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    public GuestPreferences getPreference() {
        return this.preference;
    }

    public void setPreference(GuestPreferences preference) {
        this.preference = preference;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Guest)) {
            return false;
        }
        Guest guest = (Guest) o;
        return Objects.equals(id, guest.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", fullName='" + getFullName() + "'" +
            ", email='" + getEmail() + "'" +
            ", phone='" + getPhone() + "'" +
            ", bookings='" + getBookings() + "'" +
            ", preference='" + getPreference() + "'" +
            "}";
    }
}
