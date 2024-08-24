package com.arilalale.ExpenseTracker.services.expense;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.arilalale.ExpenseTracker.dto.ExpenseDTO;
import com.arilalale.ExpenseTracker.entity.Expense;
import com.arilalale.ExpenseTracker.repository.ExpenseRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;

    public Expense postExpense(ExpenseDTO expenseDTO) {
        return saveOrUpdateExpense(new Expense(), expenseDTO);
    }

    private Expense saveOrUpdateExpense(Expense expense, ExpenseDTO expenseDTO) {
        expense.setTitle(expenseDTO.getTitle());
        expense.setDescription(expenseDTO.getDescription());
        expense.setCategory(expenseDTO.getCategory());
        expense.setDate(expenseDTO.getDate());
        expense.setAmount(expenseDTO.getAmount());

        return expenseRepository.save(expense);
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll().stream()
                .sorted(Comparator.comparing(Expense::getDate).reversed())
                .collect(Collectors.toList());
    }

    public Expense getExpenseByID(Long id) {
        Optional<Expense> optionalExpense = expenseRepository.findById(id);
        if (optionalExpense.isPresent()) {
            return optionalExpense.get();
        } else {
            throw new EntityNotFoundException("Expense is not present with ID " + id);
        }
    }

    public Expense updateExpense(Long id, ExpenseDTO expenseDTO) {
        Optional<Expense> optionalExpense = expenseRepository.findById(id);
        if (optionalExpense.isPresent()) {
            return saveOrUpdateExpense(optionalExpense.get(), expenseDTO);
        } else {
            throw new EntityNotFoundException("Expense is not present with ID " + id);
        }
    }

    public void deleteExpense(Long id) {
        Optional<Expense> optionalExpense = expenseRepository.findById(id);
        if (optionalExpense.isPresent()) {
            expenseRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Expense is not present with ID " + id);
        }
    }

    public List<Expense> filterExpensesLastWeek() {
        LocalDate currentDate = LocalDate.now();
        LocalDate oneWeekAgo = currentDate.minusWeeks(1);

        return expenseRepository.findAll().stream()
                .filter(expense -> !expense.getDate().isBefore(oneWeekAgo))
                .sorted(Comparator.comparing(Expense::getDate))
                .collect(Collectors.toList());

    }

    public List<Expense> filterExpensesLastMonth() {
        LocalDate currentDate = LocalDate.now();
        LocalDate firstDayOfLastMonth = currentDate.minusMonths(1).withDayOfMonth(1);
        LocalDate lastDayOfLastMonth = currentDate.withDayOfMonth(1).minusDays(1);

        return expenseRepository.findAll().stream()
                .filter(expense -> !expense.getDate().isBefore(firstDayOfLastMonth) &&
                        !expense.getDate().isAfter(lastDayOfLastMonth))
                .sorted(Comparator.comparing(Expense::getDate))
                .collect(Collectors.toList());

    }

    public List<Expense> filterExpensesLast3Months() {
        LocalDate currentDate = LocalDate.now();
        LocalDate firstDayOfLast3Month = currentDate.minusMonths(3).withDayOfMonth(1);
        LocalDate lastDayOfLastMonth = currentDate.withDayOfMonth(1).minusDays(1);

        return expenseRepository.findAll().stream()
                .filter(expense -> !expense.getDate().isBefore(firstDayOfLast3Month) &&
                        !expense.getDate().isAfter(lastDayOfLastMonth))
                .sorted(Comparator.comparing(Expense::getDate))
                .collect(Collectors.toList());

    }

    public List<Expense> filterExpensesByCustom(LocalDate startDate, LocalDate endDate) {
        return expenseRepository.findAll().stream()
                .filter(expense -> !expense.getDate().isBefore(startDate) &&
                        !expense.getDate().isAfter(endDate))
                .sorted(Comparator.comparing(Expense::getDate))
                .collect(Collectors.toList());

    }

    public Integer totalExpenseLastMonth() {
        LocalDate currentDate = LocalDate.now();
        LocalDate firstDayOfLastMonth = currentDate.minusMonths(1).withDayOfMonth(1);
        LocalDate lastDayOfLastMonth = currentDate.withDayOfMonth(1).minusDays(1);

        List<Expense> listExpense = expenseRepository.findAll().stream()
                                    .filter(expense -> !expense.getDate().isBefore(firstDayOfLastMonth) &&
                                    !expense.getDate().isAfter(lastDayOfLastMonth))
                                    .collect(Collectors.toList());

        int total = listExpense.stream().filter(x -> x.getAmount() > 0).mapToInt(Expense::getAmount).sum();

        return total;
    }
}
