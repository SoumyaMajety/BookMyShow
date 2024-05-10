package com.scaler.bookmyshowsoumya.dtos;

import com.scaler.bookmyshowsoumya.models.Ticket;
import lombok.Data;

@Data
public class BookTicketResponseDto {

  private  Ticket ticket;
  private String errorMessage;
  private ResponseType responseType;
}

