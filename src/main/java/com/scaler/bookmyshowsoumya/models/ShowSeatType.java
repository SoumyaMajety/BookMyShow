package com.scaler.bookmyshowsoumya.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class ShowSeatType extends BaseModel {
    @ManyToOne
    Show show;
    @Enumerated
    SeatType seatType;
    double price;

}
