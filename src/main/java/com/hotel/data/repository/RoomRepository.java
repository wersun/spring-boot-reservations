package com.hotel.data.repository;

import com.hotel.data.entity.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by wronskip on 02.07.2017.
 */
@Repository
public interface RoomRepository extends CrudRepository<Room, Long>{

    Room findByNumber(String number);
}
