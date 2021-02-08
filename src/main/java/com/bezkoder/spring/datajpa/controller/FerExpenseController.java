package com.bezkoder.spring.datajpa.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.datajpa.model.Expense;
import com.bezkoder.spring.datajpa.repository.ExpenseRepository;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class FerExpenseController {

	@Autowired
	ExpenseRepository ExpenseRepository;

	@PostMapping("/expenses")
	public ResponseEntity<Expense> addExpense(@RequestBody Expense expense) {
		try {
			Expense _expense = ExpenseRepository
					.save(new Expense(expense.getExpenseId(), expense.getType(), expense.getDate(), expense.getPrice(),
							expense.getNumberOfItems(), expense.getTotal(), expense.getByWhom()));
			return new ResponseEntity<>(_expense, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/expenses/{expense_Id}")
	public ResponseEntity<Expense> getExpenseById(@PathVariable("expense_Id") Integer expenseId) {
		Optional<Expense> expenseData = ExpenseRepository.findById(expenseId);

		if (expenseData.isPresent()) {
			return new ResponseEntity<>(expenseData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/expenses")
	public ResponseEntity<List<Expense>> getAllExpenses(@RequestParam(required = false) String type) {
		try {
			List<Expense> expenses = new ArrayList<Expense>();

			if (type == null)
				ExpenseRepository.findAll().forEach(expenses::add);
			else
				ExpenseRepository.findByTypeContaining(type).forEach(expenses::add);

			if (expenses.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(expenses, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/expenses/{expense_Id}")
	public ResponseEntity<HttpStatus> deleteExpense(@PathVariable("expense_Id") Integer expenseId) {
		try {
			ExpenseRepository.deleteByExpenseId(expenseId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
