package com.moneymoney.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.moneymoney.web.entity.Transaction;

@Controller
public class BankAppController {

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/")
	public String index() {
		return "Index";
	}

	@RequestMapping("/deposits")
	public String depositForm() {
		return "DepositForm";
	}

	@RequestMapping("/deposit")
	public String deposit(@ModelAttribute Transaction transaction, Model model) {
		restTemplate.postForEntity("http://localhost:9090/transactions", transaction, null);
		model.addAttribute("message", "Deposit Success!");
		return "DepositForm";
	}

	@RequestMapping("/withdrawal")
	public String withdrawForm() {
		return "WithdrawForm";
	}

	@RequestMapping("/withdraw")
	public String withdraw(@ModelAttribute Transaction transaction, Model model) {
		restTemplate.postForEntity("http://localhost:9090/transactions/withdraw", transaction, null);
		model.addAttribute("message", "Withdraw Success!");
		return "WithdrawForm";
	}

	@RequestMapping("/fundtransfers")
	public String fundtransfers() {
		return "FundTransferForm";
	}

	@RequestMapping("/fundtransfer")
	public String fundtransfer(@RequestParam("senderAccountNumber") int senderAccountNumber,
			@RequestParam("receiverAccountNumber") int receiverAccountNumber,
			@ModelAttribute Transaction transaction,
			Model model) {
		transaction.setAccountNumber(senderAccountNumber);
		restTemplate.postForEntity("http://localhost:9090/transactions/withdraw", transaction, null);
		transaction.setAccountNumber(receiverAccountNumber);
		restTemplate.postForEntity("http://localhost:9090/transactions", transaction, null);
		model.addAttribute("message","Transfer Success!");
		return "FundTransferForm";
	}
}
