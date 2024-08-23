package com.arilalale.ExpenseTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arilalale.ExpenseTracker.entity.Income;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {

}
