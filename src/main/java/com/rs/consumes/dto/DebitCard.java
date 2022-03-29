package com.rs.consumes.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DebitCard {

    private String idDebitCard;
    private String cardNumber;
    private List<Account> bankAccounts;
    private Integer dniUser;
    private Boolean isActive;
}
