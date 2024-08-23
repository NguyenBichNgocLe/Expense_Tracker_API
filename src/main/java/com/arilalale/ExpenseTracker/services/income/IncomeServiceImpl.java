package com.arilalale.ExpenseTracker.services.income;

import org.springframework.stereotype.Service;

import com.arilalale.ExpenseTracker.repository.IncomeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IncomeServiceImpl implements IncomeService {

    private final IncomeRepository incomeRepository;

}
