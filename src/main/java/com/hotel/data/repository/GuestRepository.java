package com.hotel.data.repository;

/**
 * Created by wronskip on 03.07.2017.
 */
import com.hotel.data.entity.Guest;
import org.springframework.data.repository.PagingAndSortingRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends PagingAndSortingRepository<Guest, Long> {

}