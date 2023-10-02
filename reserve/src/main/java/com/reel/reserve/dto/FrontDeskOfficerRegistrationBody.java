package com.reel.reserve.dto;

import jakarta.validation.constraints.NotNull;

import java.sql.Date;

public class FrontDeskOfficerRegistrationBody extends RegistrationBody{

//    @NotNull(message = "Salary must not be null.")
    private Integer salary;
}
