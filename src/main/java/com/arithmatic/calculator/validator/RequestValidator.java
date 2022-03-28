package com.arithmatic.calculator.validator;

import com.arithmatic.calculator.exception.BadRequestException;
import com.arithmatic.calculator.repository.model.CalculationData;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.util.List;

public class RequestValidator {

    @Value("${operations}")
    private List<String> operation;

    public void requestValidation(CalculationData data, String operations) throws BadRequestException {

        if(!operations.equals(data.getOperation())){
            throw new BadRequestException("Operation value is invalid, Please add valid operation: "+operation);
        }

        if(isNullOrZero(data.getFirstParam()) || isNullOrZero(data.getSecondParam())){
            throw new BadRequestException("Mandatory parameter missing");
        }
    }

    public static boolean isNullOrZero(BigDecimal number) {
        boolean isBigDecimalValueNullOrZero = false;
        if (number == null)
            isBigDecimalValueNullOrZero = true;
        else if (number != null && number.compareTo(BigDecimal.ZERO) == 0)
            isBigDecimalValueNullOrZero = true;

        return isBigDecimalValueNullOrZero;
    }
}
