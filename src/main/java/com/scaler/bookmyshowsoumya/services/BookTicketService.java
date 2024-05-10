package com.scaler.bookmyshowsoumya.services;

import com.scaler.bookmyshowsoumya.exceptions.InvalidInputException;
import com.scaler.bookmyshowsoumya.exceptions.InvalidRequestEcxeption;
import com.scaler.bookmyshowsoumya.exceptions.InvalidShowTimeException;
import com.scaler.bookmyshowsoumya.exceptions.SeatNotAvailableException;
import com.scaler.bookmyshowsoumya.models.*;
import com.scaler.bookmyshowsoumya.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
@Service
public class BookTicketService implements TicketService{

    UserRepository userRepository;
    ShowRepository showRepository;
    SeatRepository seatRepository;
    ShowSeatRepository showSeatRepository;
    TicketRepository ticketRepository;
    ShowSeatTypeRepository showSeatTypeRepository;
@Autowired
    public BookTicketService(UserRepository userRepository,ShowRepository showRepository,SeatRepository seatRepository,
                             ShowSeatRepository showSeatRepository,TicketRepository ticketRepository,
                             ShowSeatTypeRepository showSeatTypeRepository){
        this.seatRepository =seatRepository;
        this.userRepository=userRepository;
        this.showRepository=showRepository;
        this.showSeatRepository = showSeatRepository;
        this.ticketRepository=ticketRepository;
        this.showSeatTypeRepository=showSeatTypeRepository;
    }
    @Override
    public Ticket bookTicket(int userId, int showId, List<Integer> seatIds) throws InvalidShowTimeException,
            InvalidRequestEcxeption, SeatNotAvailableException {
        /*
        1. check user is present or not
        2.check show is present or not
        3. check the time limit to book tickets
        4. check seats
        5. get seats from db
        6.count no of seats
        7. if both are equal , block seats
        8. process payment
        9. book ticket
         */
        Optional<User> user = userRepository.findUserById(userId);
        if(user.isEmpty()){
            throw new InvalidRequestEcxeption("Invalid User ID");
        }
        User user1 = user.get();

       Show show =  showRepository.findShowById(showId).orElseThrow(()-> new InvalidRequestEcxeption("Invalid show ID"));

       Date curretTime = new Date();

       if(show.getStartTime().getTime()+(10*60) < curretTime.getTime() ){
           throw new InvalidShowTimeException("Show time exceeded.. Can not book tickets now");
       }
            List<Seat> seatsList = seatRepository.findAllByIdIn(seatIds);
       if(seatsList == null || seatsList.size()<seatIds.size()){
           throw new InvalidRequestEcxeption("Seats are Invalid");
       }

        // to find whether all the seats belongs to one show
                List<ShowSeat> showSeats = showSeatRepository.findAllBySeatInAndShow(seatsList,show);
                    if(seatsList.size()!=showSeats.size()){
                        throw new InvalidRequestEcxeption("Some/All requested Seats not from the single show");
                    }
            blockSeatsForUser(user1,show,seatIds);
            // getting carious seat Types and prices for that particular show
         List<ShowSeatType> showSeatTypes =  showSeatTypeRepository.findAllByShowId(showId);
         // saving price and seat type in a Map
         Map<SeatType,Double> priceList = new HashMap<>();
         // adding seattype and price to map fetched by showSeatType
                for(ShowSeatType s : showSeatTypes){
                    priceList.put(s.getSeatType(),s.getPrice());
                }
         double totalAmount =0;
        //Iterating over seats and calculating the price for each seat
         for(Seat s :seatsList){
             if(priceList.containsKey(s.getSeatType())){
                 totalAmount += priceList.get(s.getSeatType());
             }
         }

                    Ticket ticket = new Ticket();
                    ticket.setTicketStatus(TicketStatus.UNPAID);
                    ticket.setUser(user1);
                    ticket.setSeats(seatsList);
                    ticket.setTotalAmount(totalAmount);
// implement strategy pattern for additional fee

        return ticketRepository.save(ticket);
    }
   @Transactional(isolation = Isolation.SERIALIZABLE)
    public  void blockSeatsForUser(User user, Show show,List<Integer> seatIds) throws SeatNotAvailableException {

        List<ShowSeat> showSeats = showSeatRepository
                .findAllByShowIdAndSeatIdInAndSeatStatus_Available(show.getId(),seatIds);
        if(showSeats.size()!=seatIds.size()){
            throw new SeatNotAvailableException("Seats Not Available");
        }
        showSeats.stream().forEach(x-> {
                                    x.setSeatStatus(SeatStatus.BLOCKED);
                                    x.setUser(user);
        });
        // saving show seats in show seat repository
            showSeatRepository.saveAll(showSeats);

    }


}
