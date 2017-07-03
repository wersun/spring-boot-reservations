package com.hotel.web.service;

import com.hotel.business.domain.RoomReservation;
import com.hotel.business.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by wronskip on 03.07.2017.
 */
@RestController
@RequestMapping(value = "/api")
public class ReservationServiceController {

    @Autowired
    private ReservationService reservationService;

    @RequestMapping(value = "/reservations/{date}", method = RequestMethod.GET)
    public List<RoomReservation> getAllReservationsForDate(@PathVariable(value = "date") String dateString){
        return this.reservationService.getRoomReservationsForDate(dateString);
    }

}
