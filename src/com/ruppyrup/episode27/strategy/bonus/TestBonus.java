package com.ruppyrup.episode27.strategy.bonus;

public class TestBonus {
    public static void main(String[] args) {
        BonusScheme bonusScheme = new BonusTwenty();
        BonusScheme noBonus = new NoBonus();
        Employee employee20 = new Employee(bonusScheme);
        Employee employee0 = new Employee(noBonus);

        System.out.println(employee20.totalPay());
        System.out.println(employee0.totalPay());
    }
}
