package com.rs.consumes.type;

import java.math.BigDecimal;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum CreditCardType {

    DEBIT(new BigDecimal(1), "DEBIT CARD"),
    CREDIT(new BigDecimal(2), "CREDIT CARD");

    private BigDecimal creditCardTypeId;
    private String creditCardTypeDescription;

    private static final Map<BigDecimal, CreditCardType> lookup = new HashMap<>();

    static {
        EnumSet.allOf(CreditCardType.class).forEach(x -> lookup.put(x.creditCardTypeId, x));
    }

    CreditCardType(BigDecimal key, String value) {
        this.creditCardTypeId = key;
        this.creditCardTypeDescription = value;
    }

    public static CreditCardType get(BigDecimal key) {
        return lookup.get(key);
    }

    public BigDecimal getCreditCardTypeId() {
        return creditCardTypeId;
    }

    public String getCreditCardTypeDescription() {
        return creditCardTypeDescription;
    }
}
