package com.hotel.web.application;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by wronskip on 03.07.2017.
 */
@Controller
@RequestMapping(value = "/reservations")
public class ReservationController {

    @RequestMapping(method = RequestMethod.GET)
    public String getReservations(){
        return "reservations";
    }
}
