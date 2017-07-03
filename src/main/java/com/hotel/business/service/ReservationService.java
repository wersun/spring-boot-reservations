package com.hotel.business.service;

import com.hotel.business.domain.RoomReservation;
import com.hotel.data.entity.Guest;
import com.hotel.data.entity.Reservation;
import com.hotel.data.entity.Room;
import com.hotel.data.repository.GuestRepository;
import com.hotel.data.repository.ReservationRepository;
import com.hotel.data.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by wronskip on 03.07.2017.
 */
@Service
public class ReservationService {

    private final static DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");


    private RoomRepository roomRepository;
    private GuestRepository guestRepository;
    private ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<RoomReservation> getRoomReservationsForDate(String dateString){
        Date date = createDateFromDateString(dateString);
        Iterable<Room> rooms = this.roomRepository.findAll();
        Map<Long, RoomReservation> roomReservationMap = new HashMap<>();
        rooms.forEach(room -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getId());
            roomReservation.setRoomName(room.getName());
            roomReservation.setRoomNumber(room.getName());
            roomReservationMap.put(room.getId(), roomReservation);

        });

        List<Reservation> reservations = this.reservationRepository.findByDate(new java.sql.Date(date.getTime()));
        if(reservations!=null){
            reservations.forEach(reservation -> {
                Guest guest = this.guestRepository.findOne(reservation.getGuestId());
                if(guest!=null){
                    RoomReservation roomReservation = roomReservationMap.get(reservation.getId());
                    roomReservation.setDate(date);
                    roomReservation.setFirstName(guest.getFirstName());
                    roomReservation.setLastName(guest.getLastName());
                    roomReservation.setGuestId(guest.getId());
                }
            });
        }

        List<RoomReservation> roomReservations = new ArrayList<>();
        for(Long roomId : roomReservationMap.keySet()){
            roomReservations.add(roomReservationMap.get(roomId));
        }
        return roomReservations;
    }

    private Date createDateFromDateString(String dateString){
        Date date = null;
        if(dateString!=null){
            try {
                date=DATE_FORMAT.parse(dateString);
            } catch (ParseException pe) {
                date = new Date();
            }
        }else{
            date=new Date();
        }
        return date;
    }


}
