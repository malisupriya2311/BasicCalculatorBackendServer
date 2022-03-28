package com.arithmatic.calculator;

import com.arithmatic.calculator.controller.ArithmethicOperationController;
import com.arithmatic.calculator.exception.BadRequestException;
import com.arithmatic.calculator.exception.DataNotFoundException;
import com.arithmatic.calculator.repository.ArithmeticOperationRepository;
import com.arithmatic.calculator.repository.model.CalculationData;
import com.arithmatic.calculator.services.implementation.ArithmeticOperationService;
import com.arithmatic.calculator.validator.RequestValidator;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.equalTo;


@SpringBootTest
class CalculatorApplicationTests {

	@Test
	void contextLoads() {
	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Mock
	private ArithmeticOperationService arithmeticOperationService;

	@Mock
	private ArithmeticOperationRepository arithmeticOperationRepository;

	@InjectMocks
	private ArithmethicOperationController arithmethicOperationController;

	@Mock
	private RequestValidator validation;

	@Test
	void testAddition() {
		CalculationData calculationData = new CalculationData();
		calculationData.setFirstParam(new BigDecimal(6));
		calculationData.setSecondParam(new BigDecimal(2));
		calculationData.setOperation("addition");

		Assert.assertEquals(new ResponseEntity(HttpStatus.OK), arithmethicOperationController.calculateAddition(calculationData));
	}

	@Test
	void throwsExceptionWhenBadRequestAreGiven(){
		CalculationData calculationData = new CalculationData();
		calculationData.setFirstParam(new BigDecimal(6));
		calculationData.setSecondParam(new BigDecimal(2));
		calculationData.setOperation("+");

		thrown.expect(BadRequestException.class);
		thrown.expectMessage(equalTo("Operation value is invalid, Please add valid operation: [addition, subtraction, multiplication, division]"));
	}

	@Test
	void throwsExceptionWhenDataNotFound(){
		thrown.expect(DataNotFoundException.class);
		thrown.expectMessage(equalTo("Record not Found"));

		arithmethicOperationController.fetchByOperations("subtraction");
	}

	@Test
	void testSubtraction() {
		CalculationData calculationData = new CalculationData();
		calculationData.setFirstParam(new BigDecimal(10));
		calculationData.setSecondParam(new BigDecimal(2));
		calculationData.setOperation("subtraction");
		Assert.assertEquals(new ResponseEntity(HttpStatus.OK), arithmethicOperationController.calculateSubtraction(calculationData));
	}

	@Test
	void testMultiplication() {
		CalculationData calculationData = new CalculationData();
		calculationData.setFirstParam(new BigDecimal(11));
		calculationData.setSecondParam(new BigDecimal(2));
		calculationData.setOperation("multiplication");
		Assert.assertEquals(new ResponseEntity(HttpStatus.OK), arithmethicOperationController.calculateMultiplication(calculationData));
	}

	@Test
	void testDivision() {
		CalculationData calculationData = new CalculationData();
		calculationData.setFirstParam(new BigDecimal(100));
		calculationData.setSecondParam(new BigDecimal(2));
		calculationData.setOperation("division");
		Assert.assertEquals(new ResponseEntity(HttpStatus.OK), arithmethicOperationController.calculateDivision(calculationData));

		//Assert.assertEquals(new BigDecimal(100).divide(new BigDecimal(2),2,RoundingMode.HALF_UP) , arithmethicOperationController.calculateDivision(calculationData));
	}

	@Test
	void testDivisorByZero() {
		CalculationData calculationData = new CalculationData();
		calculationData.setFirstParam(new BigDecimal(100));
		calculationData.setSecondParam(new BigDecimal(0));
		calculationData.setOperation("division");
		Assert.assertEquals(new ResponseEntity(HttpStatus.OK), arithmethicOperationController.calculateDivision(calculationData));

		//Assert.assertEquals(new BigDecimal(0), arithmethicOperationController.calculateDivision(calculationData));
	}
}
