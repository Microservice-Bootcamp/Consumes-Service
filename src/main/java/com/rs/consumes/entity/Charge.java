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
@Document("charge")
public class Charge {

    @Id
    private String idCharge;
    private Integer accountNumber;
    private Double amount;
    private String concepto;

    @Builder.Default
    private LocalDate dateIssued = LocalDate.now();

    @Builder.Default
    private LocalDate dueDate = Charge.setPaymentDay();

    @Builder.Default
    private String chargeNumber = java.util.UUID.randomUUID().toString();

    @Builder.Default
    private Boolean isActive = true;


    private static LocalDate setPaymentDay(){
        LocalDate date = LocalDate.now();
        var remainingDaysToThirty =31 - date.getDayOfMonth();
        if(date.getDayOfMonth()>=3 && date.getDayOfMonth()<=15){
            return date.plusDays(remainingDaysToThirty);
        }
        else{
            return date.plusDays(remainingDaysToThirty+25);
        }

    }

}
