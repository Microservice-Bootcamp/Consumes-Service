package com.rs.consumes.webclient.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AccountDto {

    private Integer accountNumber;
    private LocalDateTime associateDebitCardDate;
    private Boolean flagPrincipal;

}
