package com.softtech.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class BankAccount {
    private Long id;
    private LocalDate transactionDate;
    private String transactionType;
    private String description;
    private BigDecimal withdrawal;
    private BigDecimal deposit;
    private BigDecimal balance;
    private String remarks;
    private LocalDateTime insertDate;
    private String path;
    
    public BankAccount() {
        this.withdrawal = new BigDecimal(0);
        this.deposit = new BigDecimal(0);
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public LocalDate getTransactionDate() {
        return transactionDate;
    }
    
    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }
    
    public String getTransactionType() {
        return transactionType;
    }
    
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public BigDecimal getWithdrawal() {
        return withdrawal;
    }
    
    public void setWithdrawal(BigDecimal withdrawal) {
        this.withdrawal = withdrawal;
    }
    
    public BigDecimal getDeposit() {
        return deposit;
    }
    
    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }
    
    public BigDecimal getBalance() {
        return balance;
    }
    
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    
    public String getRemarks() {
        return remarks;
    }
    
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    public LocalDateTime getInsertDate() {
        return insertDate;
    }
    
    public void setInsertDate(LocalDateTime insertDate) {
        this.insertDate = insertDate;
    }
    
    public String getPath() {
        return path;
    }
    
    public void setPath(String path) {
        this.path = path;
    }
}