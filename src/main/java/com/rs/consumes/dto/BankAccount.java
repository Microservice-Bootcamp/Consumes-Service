package com.rs.consumes.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BankAccount {

    private String idBankAccount;
    private Integer dniUser;
    private Integer accountNumber;
    private Integer balance;
    private String typeAccount;
    private Integer maintenanceCharge;
    private Integer movementNumber;
    private Boolean benefitStatus;
}
