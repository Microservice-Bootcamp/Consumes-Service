package com.rs.consumes.entity;

import com.rs.consumes.type.ClientType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "payment-card")
public class Payment {
    @Id
    private String id;

    private String creditCardNumber;
    private Double amount;
    private String concept;

    @Builder.Default
    private String chargeNumber = java.util.UUID.randomUUID().toString();

    @Builder.Default
    private LocalDate transactionDate = LocalDate.now();
}
