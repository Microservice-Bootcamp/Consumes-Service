package com.rs.consumes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Credit {
    private Integer dniUser;
    private Integer accountNumber;
    private Integer balance;
    private Integer creditLimit;
    private Integer debt;
    private String typeCredit;
}
