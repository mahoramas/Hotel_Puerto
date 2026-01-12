package org.docencia.hotel.web.soap.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Set;

import org.docencia.hotel.domain.api.HotelDomain;
import org.docencia.hotel.domain.model.Hotel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class HotelSoapServiceImplTest {

    @Mock
    private HotelDomain hotelDomain;

    @InjectMocks
    private HotelSoapServiceImpl hotelSoapService;

    @Test
    void getHotelByIdTestReturnHotel() {
        Long id = 1L;
        Hotel hotel = new Hotel();
        when(hotelDomain.findById(id)).thenReturn(hotel);

        Hotel result = hotelSoapService.getHotelById(id);

        assertEquals(hotel, result);
    }

    @Test
    void saveHotelTestReturnHotel() {
        Hotel hotel = new Hotel();
        when(hotelDomain.save(hotel)).thenReturn(hotel);

        Hotel result = hotelSoapService.saveHotel(hotel);

        assertEquals(hotel, result);
    }

    @Test
    void findAllHotelsTestReturnSet() {
        Set<Hotel> hotels = Set.of(new Hotel());
        when(hotelDomain.findAll()).thenReturn(hotels);

        Set<Hotel> result = hotelSoapService.findAllHotels();

        assertEquals(hotels, result);
    }

    @Test
    void deleteHotelByIdTestReturnTrue() {
        Long id = 1L;
        when(hotelDomain.deleteById(id)).thenReturn(true);

        boolean result = hotelSoapService.deleteHotelById(id);

        assertTrue(result);
    }
}
