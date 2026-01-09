package org.docencia.hotel.web.soap;

import java.util.Set;

import org.docencia.hotel.domain.model.Booking;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
/**
 * @author mahoramas
 * @version 1.0.0
 */
@WebService(
        name = "BookingSoapService",
        targetNamespace = "http://hotel.docencia.org/ws"
)
public interface BookingSoapService {

    @WebMethod(operationName = "GetBookingById")
    @WebResult(name = "booking")
    Booking getBookingById(@WebParam(name = "id") Long id);

    @WebMethod(operationName = "SaveBooking")
    @WebResult(name = "booking")
    Booking saveBooking(@WebParam(name = "booking") Booking booking);

    @WebMethod(operationName = "FindAllBookings")
    @WebResult(name = "bookings")
    Set<Booking> findAllBooking();

    @WebMethod(operationName = "DeleteBookingById")
    @WebResult(name = "success")
    boolean deleteBookingById(@WebParam(name = "id") Long id);
}
