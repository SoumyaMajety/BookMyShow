package com.scaler.bookmyshowsoumya.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
@Data
@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private Date createdAt;
    private  Date updatedAt;
}
