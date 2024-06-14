package com.suzintech.loans.controller;

import com.suzintech.loans.controller.dto.CustomerLoanRequest;
import com.suzintech.loans.controller.dto.CustomerLoanResponse;
import com.suzintech.loans.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping(value = "/customer-loans")
    public ResponseEntity<CustomerLoanResponse> customerLoans(@RequestBody @Valid CustomerLoanRequest request) {
        var response = loanService.checkLoanAvailability(request);

        return ResponseEntity.ok(response);
    }
}
