package com.ruppyrup.episode27.strategy.pricer;

import java.math.BigDecimal;

public enum Plan {
    BASIC(item -> item.price().multiply(new BigDecimal("1.025"))),
    PREMIUM(item -> item.price().multiply(new BigDecimal("1.015"))),
    BUSINESS(item -> item.price().multiply(new BigDecimal("1.005")));

    private DeliveryPriceCalculator calculator;

    Plan(DeliveryPriceCalculator calculator) {
        this.calculator = calculator;
    }

    public BigDecimal getDeliveryPrice(Item item) {
        return calculator.priceFor(item);
    }

}
