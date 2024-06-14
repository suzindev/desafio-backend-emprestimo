package com.suzintech.loans.controller.dto;

import com.suzintech.loans.domain.LoanType;

public record LoanResponse(
        LoanType type,
        Double interestRate
) {
}
