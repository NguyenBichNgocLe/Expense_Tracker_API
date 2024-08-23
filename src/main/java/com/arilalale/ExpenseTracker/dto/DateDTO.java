package com.arilalale.ExpenseTracker.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class DateDTO {

    private LocalDate startDate;

    private LocalDate endDate;
}
