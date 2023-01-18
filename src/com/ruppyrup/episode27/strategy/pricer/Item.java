package com.ruppyrup.episode27.strategy.pricer;

import java.math.BigDecimal;

public class Item {
    String name;
    BigDecimal price;

    public Item(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public BigDecimal price() {
        return price;
    }

    public String name() {
        return name;
    }
}
