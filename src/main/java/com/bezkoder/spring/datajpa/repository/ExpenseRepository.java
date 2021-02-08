package com.bezkoder.spring.datajpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bezkoder.spring.datajpa.model.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {

	List<Expense> findByTypeContaining(String type);

	void deleteByExpenseId(Integer expenseId);
	
//	List<User> findByUsernameContaining(String username);
	

	
	
}
