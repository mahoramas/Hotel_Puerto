package org.docencia.hotel.web.soap.impl;

import java.util.Set;

import org.docencia.hotel.domain.api.HotelDomain;
import org.docencia.hotel.domain.model.Hotel;
import org.docencia.hotel.web.soap.HotelSoapService;
import org.springframework.stereotype.Service;

import jakarta.jws.WebService;
/**
 * @author mahoramas
 * @version 1.0.0
 */
@Service
@WebService(
        endpointInterface = "org.docencia.hotel.web.soap.HotelSoapService",
        targetNamespace = "http://hotel.docencia.org/ws",
        serviceName = "HotelSoapService",
        portName = "HotelSoapPort"
)
public class HotelSoapServiceImpl implements HotelSoapService {

    private final HotelDomain hotelDomain;

    public HotelSoapServiceImpl(HotelDomain hotelDomain) {
        this.hotelDomain = hotelDomain;
    }

    @Override
    public Hotel getHotelById(Long id) {
        return hotelDomain.findById(id);
    }

    @Override
    public Hotel saveHotel(Hotel hotel) {
        return hotelDomain.save(hotel);
    }

    @Override
    public Set<Hotel> findAllHotels() {
        return hotelDomain.findAll();
    }

    @Override
    public boolean deleteHotelById(Long id) {
        return hotelDomain.deleteById(id);
    }

}
