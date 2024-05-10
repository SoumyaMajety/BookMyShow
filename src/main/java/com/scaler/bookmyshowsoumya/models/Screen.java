package com.scaler.bookmyshowsoumya.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;
@Data
@Entity
public class Screen extends BaseModel{
    private  int screenNo;
    @OneToMany(mappedBy = "screen")
    private List<Seat> seat;
    @ManyToOne
   private Theatre theatre;

}
