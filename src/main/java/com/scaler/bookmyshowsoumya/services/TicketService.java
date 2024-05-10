package com.scaler.bookmyshowsoumya.services;

import com.scaler.bookmyshowsoumya.exceptions.DataNotFoundException;
import com.scaler.bookmyshowsoumya.exceptions.InvalidRequestEcxeption;
import com.scaler.bookmyshowsoumya.exceptions.InvalidShowTimeException;
import com.scaler.bookmyshowsoumya.exceptions.SeatNotAvailableException;
import com.scaler.bookmyshowsoumya.models.Ticket;

import java.util.List;

public interface TicketService {



    public Ticket bookTicket(int userId, int showId, List<Integer> seats) throws InvalidShowTimeException, InvalidRequestEcxeption, SeatNotAvailableException;

}
