package com.ruppyrup.episode27.strategy.pricer;

import java.math.BigDecimal;

public class TestStrategy {
    public static void main(String[] args) {

        Item tenisBall = new Item("Tennis Ball", new BigDecimal(5.0));

        System.out.println(Plan.BASIC.getDeliveryPrice(tenisBall));
        System.out.println(Plan.PREMIUM.getDeliveryPrice(tenisBall));
        System.out.println(Plan.BUSINESS.getDeliveryPrice(tenisBall));
    }
}
