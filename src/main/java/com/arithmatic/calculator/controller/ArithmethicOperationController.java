package com.arithmatic.calculator.controller;

import com.arithmatic.calculator.constants.Constant;
import com.arithmatic.calculator.exception.BadRequestException;
import com.arithmatic.calculator.exception.DataNotFoundException;
import com.arithmatic.calculator.repository.model.CalculationData;
import com.arithmatic.calculator.services.implementation.ArithmeticOperationService;
import com.arithmatic.calculator.validator.RequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ArithmethicOperationController {

    @Autowired
    private ArithmeticOperationService arithmeticOperationService;

    @Autowired
    private RequestValidator validation;

    @PostMapping("/addition")
    public ResponseEntity calculateAddition(@RequestBody CalculationData data) {
        try {
            validation.requestValidation(data,Constant.ADD);
        }catch(BadRequestException badRequestException){
            return new ResponseEntity(badRequestException.getErrorMsg(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(arithmeticOperationService.calculateArithmeticOperation(data),HttpStatus.OK);
    }

    @RequestMapping("/operation/{operation}")
    public ResponseEntity fetchByOperations(@PathVariable String operation) {
        try{
            return new ResponseEntity(arithmeticOperationService.fetchByOperations(operation),HttpStatus.OK);
        }catch(DataNotFoundException dataNotFoundException ){
            return new ResponseEntity(dataNotFoundException.getErrorMsg(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping("/all")
    public ResponseEntity fetchAllRecords(){
        return new ResponseEntity(arithmeticOperationService.getAllRecords(),HttpStatus.OK);
    }

    @PostMapping("/subtraction")
    public ResponseEntity calculateSubtraction(@RequestBody CalculationData data){
        try {
            validation.requestValidation(data, Constant.SUBTRACT);
        }catch(BadRequestException badRequestException){
            return new ResponseEntity(badRequestException.getErrorMsg(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(arithmeticOperationService.calculateArithmeticOperation(data),HttpStatus.OK);
    }

    @PostMapping("/multiplication")
    public ResponseEntity calculateMultiplication(@RequestBody CalculationData data){
        try {
            validation.requestValidation(data, Constant.MULTIPLY);
        } catch(BadRequestException badRequestException){
            return new ResponseEntity(badRequestException.getErrorMsg(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(arithmeticOperationService.calculateArithmeticOperation(data),HttpStatus.OK);
    }

    @PostMapping("/division")
    public ResponseEntity calculateDivision(@RequestBody CalculationData data){
        try {
            validation.requestValidation(data, Constant.DIVIDE);
        }catch(BadRequestException badRequestException){
            return new ResponseEntity(badRequestException.getErrorMsg(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(arithmeticOperationService.calculateArithmeticOperation(data),HttpStatus.OK);
    }
}

