package com.scaler.bookmyshowsoumya.controllers;


import com.scaler.bookmyshowsoumya.dtos.BookTicketRequestDto;
import com.scaler.bookmyshowsoumya.dtos.BookTicketResponseDto;
import com.scaler.bookmyshowsoumya.dtos.ResponseType;
import com.scaler.bookmyshowsoumya.exceptions.InvalidInputException;
import com.scaler.bookmyshowsoumya.models.Ticket;
import com.scaler.bookmyshowsoumya.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TicketController {
    TicketService ticketService;
@Autowired
    public TicketController(TicketService ticketService){
        this.ticketService=ticketService;
    }

    public BookTicketResponseDto bookTicket(BookTicketRequestDto requestDto)  {
        BookTicketResponseDto responseDto = new BookTicketResponseDto();
        try {
            validate(requestDto);
            Ticket t = ticketService.bookTicket(requestDto.getUserId(),requestDto.getShowId(),requestDto.getSeatIds());
            responseDto = convertTicketToResponseDto(t);
        }
        catch (Exception e) {
            responseDto.setResponseType(ResponseType.FAILURE);
            responseDto.setErrorMessage(e.getMessage());
            System.out.println(e.getMessage());
        }


        return responseDto;
    }

    private BookTicketResponseDto convertTicketToResponseDto(Ticket t) {
        BookTicketResponseDto responseDto = new BookTicketResponseDto();
        responseDto.setTicket(t);
        responseDto.setResponseType(ResponseType.SUCCESS);
        return responseDto;
    }

    public void validate(BookTicketRequestDto requestDto) throws InvalidInputException {
        if(requestDto.getSeatIds().equals(null) )
         throw new InvalidInputException("Seats cannot be null");
        if(requestDto.getUserId()<0)
            throw new InvalidInputException("User ID is not valid");
    }
}
