package com.scaler.bookmyshowsoumya.repository;

import com.scaler.bookmyshowsoumya.models.Seat;
import com.scaler.bookmyshowsoumya.models.Show;
import com.scaler.bookmyshowsoumya.models.ShowSeat;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat,Integer> {


    List<ShowSeat> findAllBySeatInAndShow(List<Seat> seats,Show show);
@Lock(value = LockModeType.PESSIMISTIC_READ)
    List<ShowSeat> findAllByShowIdAndSeatIdInAndSeatStatus_Available(int showId, List<Integer> seatIds);


}
