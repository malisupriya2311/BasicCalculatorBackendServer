package com.arithmatic.calculator.services.implementation;

import com.arithmatic.calculator.constants.Constant;
import com.arithmatic.calculator.exception.DataNotFoundException;
import com.arithmatic.calculator.repository.ArithmeticOperationRepository;
import com.arithmatic.calculator.repository.model.CalculationData;
import com.arithmatic.calculator.services.interfaces.IArithmeticOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArithmeticOperationService implements IArithmeticOperation {

    @Autowired
    private ArithmeticOperationRepository arithmeticOperationRepository;

    BigDecimal finalResult = null;
    List<CalculationData> recordsDetail = new ArrayList<>();

    public List<CalculationData> getAllRecords(){
        return (List<CalculationData>) arithmeticOperationRepository.findAll();
    }

    public List<CalculationData> fetchByOperations(String operation) throws DataNotFoundException {
        if (arithmeticOperationRepository.findByOperation(operation).isEmpty()) {
            throw new DataNotFoundException("");
        }
        return arithmeticOperationRepository.findByOperation(operation);
    }

    public BigDecimal calculateArithmeticOperation(CalculationData data) {
        switch (data.getOperation()) {
            case Constant.ADD:
                additionMultiptnOperation(recordsDetail,data).ifPresentOrElse(
                        (value)-> { value.getResult(); },
                        ()-> { persistResult(data,data.getFirstParam().add(data.getSecondParam()),recordsDetail); });
                break;
            case Constant.SUBTRACT:
                commonOperation(recordsDetail,data).ifPresentOrElse(opt -> opt.getResult(),
                        () -> persistResult(data,data.getFirstParam().subtract(data.getSecondParam()),recordsDetail));
                break;
            case Constant.MULTIPLY:
                additionMultiptnOperation(recordsDetail,data).ifPresentOrElse(opt -> opt.getResult(),
                        () -> persistResult(data,data.getFirstParam().multiply(data.getSecondParam()),recordsDetail));
                break;
            case Constant.DIVIDE:
                commonOperation(recordsDetail,data).ifPresentOrElse(opt -> opt.getResult(),
                        () -> persistResult(data,divisionOperation(data),recordsDetail));
                break;
        }
        return finalResult;
    }

    private BigDecimal divisionOperation(CalculationData data) {
        if (data.getSecondParam().equals(BigDecimal.ZERO)) {
            return new BigDecimal(0);
        }else {
            return data.getFirstParam().divide(data.getSecondParam(),2, RoundingMode.HALF_UP);
        }
    }

    private Optional<CalculationData> commonOperation(List<CalculationData> recordsDetail, CalculationData data) {
        return recordsDetail.stream().filter(val -> val.getFirstParam().compareTo(data.getFirstParam()) == 0
                && val.getSecondParam().compareTo(data.getSecondParam()) == 0
                && (val.getOperation().equals(data.getOperation()))).findAny();

    }

    private Optional<CalculationData> additionMultiptnOperation(List<CalculationData> recordsDetail, CalculationData data) {
                return recordsDetail.stream().filter(val -> (val.getFirstParam().compareTo(data.getFirstParam()) == 0 || val.getFirstParam().compareTo(data.getSecondParam())== 0)
                && (val.getSecondParam().compareTo(data.getFirstParam()) == 0 || val.getSecondParam().compareTo(data.getSecondParam()) == 0)
                && (val.getOperation().equals(data.getOperation()))).findAny();
    }

    private void persistResult(CalculationData data, BigDecimal result, List<CalculationData> recordsDetail) {
            finalResult = result;
            data.setResult(result);
            recordsDetail.add(data);
            arithmeticOperationRepository.save(data);
    }

}
