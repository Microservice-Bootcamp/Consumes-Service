package com.rs.consumes.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "payment-debt")
public class Debt {
    @Id
    private String id;

    private Integer accountNumber;
    private Integer amount;
    private Integer payerID;

    @Builder.Default
    private LocalDate date = LocalDate.now();
}
