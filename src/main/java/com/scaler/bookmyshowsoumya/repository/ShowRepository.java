package com.scaler.bookmyshowsoumya.repository;

import com.scaler.bookmyshowsoumya.models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ShowRepository extends JpaRepository<Show,Integer> {

    Optional<Show> findShowById(int showId);
}
