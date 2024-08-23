package com.arilalale.ExpenseTracker.services.expense;

import java.time.LocalDate;
import java.util.List;

import com.arilalale.ExpenseTracker.dto.ExpenseDTO;
import com.arilalale.ExpenseTracker.entity.Expense;

public interface ExpenseService {

    Expense postExpense(ExpenseDTO expenseDTO);

    List<Expense> getAllExpenses();

    Expense getExpenseByID(Long id);

    Expense updateExpense(Long id, ExpenseDTO expenseDTO);

    void deleteExpense(Long id);

    List<Expense> filterExpensesLastWeek();

    List<Expense> filterExpensesLastMonth();

    List<Expense> filterExpensesLast3Months();

    List<Expense> filterExpensesByCustom(LocalDate startDate, LocalDate endDate);
}
