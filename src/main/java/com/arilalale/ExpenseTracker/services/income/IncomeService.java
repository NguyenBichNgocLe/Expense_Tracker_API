package com.arilalale.ExpenseTracker.services.income;

import java.util.List;

import com.arilalale.ExpenseTracker.dto.IncomeDTO;
import com.arilalale.ExpenseTracker.entity.Income;

public interface IncomeService {

    Income postIncome(IncomeDTO incomeDTO);

    List<Income> getAllIncomes();

    Income getIncomeByID(Long id);

    Income updateIncome(Long id, IncomeDTO incomeDTO);

    void deleteIncome(Long id);

    List<Income> filterIncomesLastMonth();
}
