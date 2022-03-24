package com.rs.consumes.type;

import java.math.BigDecimal;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum ConsumeType {

    WITHDRAWAL(new BigDecimal(1), "WITHDRAWAL"),
    PAYMENT(new BigDecimal(2), "PAYMENT");

    private BigDecimal consumeTypeId;
    private String consumeDescription;

    private static final Map<BigDecimal, ConsumeType> lookup = new HashMap<>();

    static {
        EnumSet.allOf(ConsumeType.class).forEach(x -> lookup.put(x.consumeTypeId, x));
    }

    ConsumeType(BigDecimal key, String value) {
        this.consumeTypeId = key;
        this.consumeDescription = value;
    }

    public static ConsumeType get(BigDecimal key) {
        return lookup.get(key);
    }

    public BigDecimal getConsumeTypeId() {
        return consumeTypeId;
    }

    public String getConsumeDescription() {
        return consumeDescription;
    }
}
