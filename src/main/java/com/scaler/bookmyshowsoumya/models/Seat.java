package com.scaler.bookmyshowsoumya.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Seat extends BaseModel{
    private int seatNo;
    @Enumerated
    private  SeatType seatType;
    @ManyToOne
    private Screen screen;
}
