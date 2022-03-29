package com.rs.consumes.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Account {
    private Integer accountNumber;
    private LocalDateTime associateDebitCardDate;
    private Boolean flagPrincipal;
}
