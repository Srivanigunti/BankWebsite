package com.moneymoney.app.transactionsservice.service;

import java.time.LocalDate;
import java.util.List;

import com.moneymoney.app.transactionsservice.entity.Transaction;

public interface TransactionService {

	Double withdraw(int accountNumber, String transactioDetails, double currentBalance, double amount);

	Double deposit(int accountNumber, String transactioDetails, double currentBalance, double amount);

}