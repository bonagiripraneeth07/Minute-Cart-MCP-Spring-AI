package com.minuteCart.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {
    private int userId;
    private String userName;
    private String address;
    private String mobileNumber;

}
