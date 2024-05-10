package com.scaler.bookmyshowsoumya.repository;


import com.scaler.bookmyshowsoumya.models.Show;
import com.scaler.bookmyshowsoumya.models.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ShowSeatTypeRepository extends JpaRepository<ShowSeatType,Integer> {
    List<ShowSeatType> findAllByShowId(int showId);
}
