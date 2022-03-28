package com.arithmatic.calculator.repository.model;

import org.springframework.cache.annotation.CacheConfig;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class CalculationData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String operation;
    private BigDecimal firstParam;
    private BigDecimal secondParam;
    private BigDecimal result;

    public CalculationData() {
    }

    public CalculationData(int id,String operation, BigDecimal firstParam, BigDecimal secondParam,BigDecimal result) {
        this.id = id;
        this.operation = operation;
        this.firstParam = firstParam;
        this.secondParam = secondParam;
        this.result = result;
    }

    public BigDecimal getResult() {
        return result;
    }

    public void setResult(BigDecimal result) {
        this.result = result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public BigDecimal getSecondParam() {
        return secondParam;
    }

    public void setSecondParam(BigDecimal secondParam) {
        this.secondParam = secondParam;
    }

    public BigDecimal getFirstParam() {
        return firstParam;
    }

    public void setFirstParam(BigDecimal firstParam) {
        this.firstParam = firstParam;
    }
}
