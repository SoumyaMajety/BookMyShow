package com.scaler.bookmyshowsoumya.repository;

import com.scaler.bookmyshowsoumya.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SeatRepository extends JpaRepository<Seat,Integer> {

    List<Seat> findAllByIdIn(List<Integer> seatIds);
}
