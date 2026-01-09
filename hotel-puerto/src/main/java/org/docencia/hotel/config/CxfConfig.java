package org.docencia.hotel.config;

import jakarta.xml.ws.Endpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.docencia.hotel.web.soap.impl.GuestSoapServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CxfConfig {

    private final BookingSoapServiceImpl bookingSoapEndPoint;
    private final RoomSoapServiceImpl roomSoapEndPoint;
    private final GuestSoapServiceImpl guestSoapEndPoint;
    private final HotelSoapServiceImpl hotelSoapEndPoint;
    private final Bus bus;

    public CxfConfig(BookingSoapServiceImpl bookingSoapEndPoint, Bus bus, GuestSoapServiceImpl guestSoapEndPoint, HotelSoapServiceImpl hotelSoapEndPoint, RoomSoapServiceImpl roomSoapEndPoint) {
        this.bookingSoapEndPoint = bookingSoapEndPoint;
        this.bus = bus;
        this.guestSoapEndPoint = guestSoapEndPoint;
        this.hotelSoapEndPoint = hotelSoapEndPoint;
        this.roomSoapEndPoint = roomSoapEndPoint;
    }


    @Bean
    public Endpoint guestEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, guestSoapEndPoint);
        endpoint.publish("/guest");
        return endpoint;
    }

    @Bean
    public Endpoint bookingEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, bookingSoapEndPoint);
        endpoint.publish("/booking");
        return endpoint;
    }

    @Bean
    public Endpoint hotelEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, hotelSoapEndPoint);
        endpoint.publish("/hotel");
        return endpoint;
    }

    @Bean
    public Endpoint roomEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, roomSoapEndPoint);
        endpoint.publish("/room");
        return endpoint;
    }
}
