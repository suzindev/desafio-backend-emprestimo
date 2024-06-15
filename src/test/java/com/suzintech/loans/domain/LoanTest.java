package com.suzintech.loans.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LoanTest {

    @Mock
    private Customer customer;

    @InjectMocks
    private Loan loan;

    @Nested
    class isPersonalLoanAvailable {
        @Test
        void shouldBeAvailableWhenIncomeIsEqualOrLess3k() {
            Mockito.doReturn(true).when(customer).isIncomeEqualOrLowerThan(3000.0);

            Assertions.assertTrue(loan.isPersonalLoanAvailable());
        }

        @Test
        void shouldBeAvailableWhenIncomeIsBetween3kAnd5kAndAgeIsLessThan30AndLocationIsSP() {
            Mockito.doReturn(false).when(customer).isIncomeEqualOrLowerThan(3000.0);
            Mockito.doReturn(true).when(customer).isIncomeBetween(3000.0, 5000.0);
            Mockito.doReturn(true).when(customer).isAgeLowerThan(30);
            Mockito.doReturn(true).when(customer).isFromLocation("SP");

            Assertions.assertTrue(loan.isPersonalLoanAvailable());
        }
    }

    @Nested
    class isGuaranteedLoanAvailable {
        @Test
        void shouldBeAvailableWhenIncomeIsEqualOrLess3k() {
            Mockito.doReturn(true).when(customer).isIncomeEqualOrLowerThan(3000.0);

            Assertions.assertTrue(loan.isGuaranteedLoanAvailable());
        }

        @Test
        void shouldBeAvailableWhenIncomeIsBetween3kAnd5kAndAgeIsLessThan30AndLocationIsSP() {
            Mockito.doReturn(false).when(customer).isIncomeEqualOrLowerThan(3000.0);
            Mockito.doReturn(true).when(customer).isIncomeBetween(3000.0, 5000.0);
            Mockito.doReturn(true).when(customer).isAgeLowerThan(30);
            Mockito.doReturn(true).when(customer).isFromLocation("SP");

            Assertions.assertTrue(loan.isGuaranteedLoanAvailable());
        }
    }

    @Nested
    class isConsigmentLoanAvailable {
        @Test
        void shouldBeAvailableWhenIncomeIsEqualOrGreaterThan5k() {
            Mockito.doReturn(true).when(customer).isIncomeEqualOrGreaterThan(5000.0);

            Assertions.assertTrue(loan.isConsignmentLoanAvailable());
        }

        @Test
        void shouldNotBeAvailableWhenIncomeIsEqualTo4k() {
            Mockito.doReturn(false).when(customer).isIncomeEqualOrGreaterThan(5000.0);

            Assertions.assertFalse(loan.isConsignmentLoanAvailable());
        }
    }

    @Nested
    class getPersonalLoanInterestRate {
        @Test
        void shouldTheInterestRateBe4() {
            Mockito.doReturn(true).when(customer).isIncomeEqualOrLowerThan(3000.0);

            Assertions.assertEquals(4.0, loan.getPersonalLoanInterestRate());
        }

        @Test
        void shouldThrowExceptionWhenIsNotAvailable() {
            Mockito.doReturn(false).when(customer).isIncomeEqualOrLowerThan(3000.0);

            Assertions.assertThrows(LoanNotAvailableException.class, () -> loan.getPersonalLoanInterestRate());
        }
    }

    @Nested
    class getGuaranteedLoanInterestRate {
        @Test
        void shouldTheInterestRateBe3() {
            Mockito.doReturn(true).when(customer).isIncomeEqualOrLowerThan(3000.0);

            Assertions.assertEquals(3.0, loan.getGuaranteedLoanInterestRate());
        }

        @Test
        void shouldThrowExceptionWhenIsNotAvailable() {
            Mockito.doReturn(false).when(customer).isIncomeEqualOrLowerThan(3000.0);

            Assertions.assertThrows(LoanNotAvailableException.class, () -> loan.getGuaranteedLoanInterestRate());
        }
    }

    @Nested
    class getConsigmentLoanInterestRate {
        @Test
        void shouldTheInterestRateBe2() {
            Mockito.doReturn(true).when(customer).isIncomeEqualOrGreaterThan(5000.0);

            Assertions.assertEquals(2.0, loan.getConsignmentLoanInterestRate());
        }

        @Test
        void shouldThrowExceptionWhenIsNotAvailable() {
            Mockito.doReturn(false).when(customer).isIncomeEqualOrGreaterThan(5000.0);

            Assertions.assertThrows(LoanNotAvailableException.class, () -> loan.getConsignmentLoanInterestRate());
        }
    }
}