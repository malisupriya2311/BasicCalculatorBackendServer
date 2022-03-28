package com.arithmatic.calculator.services.interfaces;

import com.arithmatic.calculator.exception.DataNotFoundException;
import com.arithmatic.calculator.repository.model.CalculationData;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

public interface IArithmeticOperation {
    BigDecimal calculateArithmeticOperation(CalculationData data);
    public List<CalculationData> fetchByOperations(String operation) throws DataNotFoundException;
    public List<CalculationData> getAllRecords();
}
