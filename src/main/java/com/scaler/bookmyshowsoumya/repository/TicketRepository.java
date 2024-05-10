package com.scaler.bookmyshowsoumya.repository;

import com.scaler.bookmyshowsoumya.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Integer> {
}
