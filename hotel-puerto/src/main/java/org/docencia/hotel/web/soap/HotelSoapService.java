package org.docencia.hotel.web.soap;

import java.util.Set;

import org.docencia.hotel.domain.model.Hotel;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
/**
 * @author mahoramas
 * @version 1.0.0
 */
@WebService(
        name = "HotelSoapService",
        targetNamespace = "http://hotel.docencia.org/ws"
)
public interface HotelSoapService {
    @WebMethod(operationName = "GetHotelById")
    @WebResult(name = "hotel")
    Hotel getHotelById(@WebParam(name = "id") Long id);

    @WebMethod(operationName = "SaveHotel")
    @WebResult(name = "hotel")
    Hotel saveHotel(@WebParam(name = "hotel") Hotel hotel);

    @WebMethod(operationName = "FindAllHotels")
    @WebResult(name = "hotels")
    Set<Hotel> findAllHotels();

    @WebMethod(operationName = "DeleteHotelById")
    @WebResult(name = "success")
    boolean deleteHotelById(@WebParam(name = "id") Long id);
}
