package com.scaler.bookmyshowsoumya.models;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class User extends BaseModel{
    private  String userName;
    private  String email;

}
