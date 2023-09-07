package com.boot.order.model.entity;

import lombok.Getter;

@Getter
public enum PaymentType {

    CASH(1, "Cash"),
    CARD(2, "Card"),
    CHEQUE(3, "Cheque"),
    YET_TO_PAY(4, "Yet to Pay");

    private int type;
    private String value;

    PaymentType(int type, String value) {
        this.type = type;
        this.value = value;
    }

//    public static PaymentType getPaymentTypeByType(Long type) {
//
//    }
}
