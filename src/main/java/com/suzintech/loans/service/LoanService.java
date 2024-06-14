package com.suzintech.loans.service;

import com.suzintech.loans.controller.dto.CustomerLoanRequest;
import com.suzintech.loans.controller.dto.CustomerLoanResponse;
import com.suzintech.loans.controller.dto.LoanResponse;
import com.suzintech.loans.domain.Loan;
import com.suzintech.loans.domain.LoanType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoanService {

    public CustomerLoanResponse checkLoanAvailability(CustomerLoanRequest request) {
        var customer = request.toCustomer();
        var loan = new Loan(customer);

        List<LoanResponse> loans = new ArrayList<>();

        if (loan.isPersonalLoanAvailable()) {
            loans.add(new LoanResponse(LoanType.PERSONAL, loan.getPersonalLoanInterestRate()));
        }

        if (loan.isConsignmentLoanAvailable()) {
            loans.add(new LoanResponse(LoanType.CONSIGNMENT, loan.getConsignmentLoanInterestRate()));
        }

        if (loan.isGuaranteedLoanAvailable()) {
            loans.add(new LoanResponse(LoanType.GUARANTEED, loan.getGuaranteedLoanInterestRate()));
        }

        return new CustomerLoanResponse(request.name(), loans);
    }
}
