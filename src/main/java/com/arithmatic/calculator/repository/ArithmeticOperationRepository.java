package com.arithmatic.calculator.repository;

import com.arithmatic.calculator.repository.model.CalculationData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArithmeticOperationRepository extends CrudRepository<CalculationData,String> {

    List<CalculationData> findByOperation(String operation);
}
