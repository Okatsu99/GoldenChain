package com.remd.spring.controller.rest;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.remd.spring.repository.ProcedureRepository;

@RestController
public class ReceiptRestController {
	@Autowired
	private ProcedureRepository procedureRepository;
	@GetMapping(path = "/app/receipt/addprocedure/price", produces = "application/json")
	public BigDecimal getProcedurePrice(@RequestParam(name = "procedureId") Integer procedureId) {
		// Ideally this Exception should be in its own class.
		return procedureRepository.findPriceById(procedureId)
				.orElseThrow(() -> new RuntimeException("Procedure could not be found"));
	}
}
