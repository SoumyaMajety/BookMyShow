package com.scaler.bookmyshowsoumya.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.List;
@Data
@Entity
public class Movie extends BaseModel {
    private  String MovieName;
    private Genre genre;

    private List<String> actors;

    private List<String> directors;

}
