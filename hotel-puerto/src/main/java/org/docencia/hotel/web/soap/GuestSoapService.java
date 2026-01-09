package org.docencia.hotel.web.soap;

import java.util.Set;

import org.docencia.hotel.domain.model.Guest;
import org.docencia.hotel.domain.model.GuestPreferences;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
/**
 * @author mahoramas
 * @version 1.0.0
 */
@WebService(
        name = "GuestSoapService",
        targetNamespace = "http://hotel.docencia.org/ws"
)
public interface GuestSoapService {

@WebMethod(operationName = "GetGuestById")
    @WebResult(name = "guest")
    Guest getGuestById(@WebParam(name = "id") Long id);

    @WebMethod(operationName = "SaveGuest")
    @WebResult(name = "guest")
    Guest saveGuest(@WebParam(name = "guest") Guest guest);

    @WebMethod(operationName = "FindAllGuests")
    @WebResult(name = "guests")
    Set<Guest> findAllGuests();

    @WebMethod(operationName = "DeleteGuestById")
    @WebResult(name = "success")
    boolean deleteGuestById(@WebParam(name = "id") Long id);

    @WebMethod(operationName = "SaveGuestPreferences")
    @WebResult(name = "preferences")
    GuestPreferences saveGuestPreferences(@WebParam(name = "guestId") Long guestId,
    @WebParam(name = "preference") GuestPreferences preferences);

    @WebMethod(operationName = "DeletePreferencesById")
    @WebResult(name = "success")
    boolean deletePreferenceById(@WebParam(name = "guestId") Long guestId);

    @WebMethod(operationName = "GetPreferenceById")
    @WebResult(name = "preference")
    GuestPreferences findPreferenceById(@WebParam(name = "guestId") Long guestId);
}
