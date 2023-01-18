package com.ruppyrup.episode27.strategy.bonus;

public interface BonusScheme {
    void apply(Money pay);
}

class NoBonus implements BonusScheme {

    @Override
    public void apply(Money pay) {

    }
}

class BonusTwenty implements BonusScheme {

    @Override
    public void apply(Money pay) {
        pay.setAmount(pay.getAmount() + 20);
    }
}
