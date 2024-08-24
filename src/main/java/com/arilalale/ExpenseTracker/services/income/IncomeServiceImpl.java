package com.arilalale.ExpenseTracker.services.income;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.arilalale.ExpenseTracker.dto.IncomeDTO;
import com.arilalale.ExpenseTracker.entity.Income;
import com.arilalale.ExpenseTracker.repository.IncomeRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IncomeServiceImpl implements IncomeService {

    private final IncomeRepository incomeRepository;

    public Income postIncome(IncomeDTO incomeDTO) {
        return saveOrUpdateIncome(new Income(), incomeDTO);
    }

    private Income saveOrUpdateIncome(Income income, IncomeDTO incomeDTO) {
        income.setTitle(incomeDTO.getTitle());
        income.setDescription(incomeDTO.getDescription());
        income.setCategory(incomeDTO.getCategory());
        income.setDate(incomeDTO.getDate());
        income.setAmount(incomeDTO.getAmount());

        return incomeRepository.save(income);
    }

    public List<Income> getAllIncomes() {
        return incomeRepository.findAll().stream()
                .sorted(Comparator.comparing(Income::getDate).reversed())
                .collect(Collectors.toList());
    }

    public Income getIncomeByID(Long id) {
        Optional<Income> optionalIncome = incomeRepository.findById(id);
        if (optionalIncome.isPresent()) {
            return optionalIncome.get();
        } else {
            throw new EntityNotFoundException("Income with the ID " + id + " not found");
        }
    }

    public Income updateIncome(Long id, IncomeDTO incomeDTO) {
        Optional<Income> optionalIncome = incomeRepository.findById(id);
        if (optionalIncome.isPresent()) {
            return saveOrUpdateIncome(optionalIncome.get(), incomeDTO);
        } else {
            throw new EntityNotFoundException("Income with the ID " + id + " not found");
        }
    }

    public void deleteIncome(Long id) {
        Optional<Income> optionalIncome = incomeRepository.findById(id);
        if (optionalIncome.isPresent()) {
            incomeRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Income with the ID " + id + " not found");
        }
    }

    public List<Income> filterIncomesLastMonth() {
        LocalDate currentDate = LocalDate.now();
        LocalDate firstDayOfLastMonth = currentDate.minusMonths(1).withDayOfMonth(1);
        LocalDate lastDayOfLastMonth = currentDate.withDayOfMonth(1).minusDays(1);

        return incomeRepository.findAll().stream()
                .filter(income -> !income.getDate().isBefore(firstDayOfLastMonth) &&
                        !income.getDate().isAfter(lastDayOfLastMonth))
                .sorted(Comparator.comparing(Income::getDate))
                .collect(Collectors.toList());
    }
}
