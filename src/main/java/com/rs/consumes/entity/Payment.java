package com.rs.consumes.entity;

import com.rs.consumes.type.ClientType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Document
public class Payment {

    @Id
    private String id;

    private String creditCardNumber;

    private BigDecimal amount;

    private String chargeNumber;

    private LocalDateTime transactionDate;

    private ClientType clientType;
}
