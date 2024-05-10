package com.scaler.bookmyshowsoumya.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.List;
@Data
@Entity
public class Ticket extends BaseModel{
   @ManyToOne
   private User user;
@ManyToMany
    private List<Seat> seats;
@ManyToOne
    private Show show;
@Enumerated
private TicketStatus ticketStatus;
double totalAmount;
//    private List<Payment> payments;


}
