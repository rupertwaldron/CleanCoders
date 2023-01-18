package com.ruppyrup.episode27.strategy.pricer;

import java.math.BigDecimal;

@FunctionalInterface
public interface DeliveryPriceCalculator {
    BigDecimal priceFor(Item item);
}
