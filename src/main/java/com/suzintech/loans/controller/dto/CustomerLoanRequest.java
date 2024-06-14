package com.suzintech.loans.controller.dto;

import com.suzintech.loans.domain.Customer;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record CustomerLoanRequest(
        @NotNull @Min(value = 18) Integer age,
        @CPF String cpf,
        @NotBlank String name,
        @NotNull @Min(value = 1000) Double income,
        @NotBlank String location
) {
    public Customer toCustomer() {
        return new Customer(age, cpf, name, income, location);
    }
}
