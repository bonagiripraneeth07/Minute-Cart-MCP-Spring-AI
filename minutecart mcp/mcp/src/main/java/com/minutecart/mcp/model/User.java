package com.minutecart.mcp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {
    private int userId;
    private String userName;
    private String address;
    private String mobileNumber;

}
