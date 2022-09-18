package com.ruppyrup.episode4;

import java.util.Map;
import java.util.Random;
import java.util.function.UnaryOperator;
import java.util.stream.IntStream;

public class FortuneTellerDeCoupled {
    private final Map<Integer, String> fortunes = Map.of(
            1, "A faithful friend is a strong defense.",
            2, "A friend asks only for your time not your money.",
            3,  "A gambler not only will lose what he has, but also will lose what he doesn’t have.",
            4, "The more you practice the luckier you get",
            5, "Give the fortune teller your watch"
    );
    private final Map<Integer, String> fortunesThatCollectMoreMoney = Map.of(
            1, "Give the fortune teller your ring",
            2, "Give the fortune teller your watch"
    );
    private final Random random = new Random();
    private boolean hasCrossedPalmWithSilver;
    private boolean hasClient;

    public String welcomeClient(float payment) { // to remove temporal coupling
        takePaymentFromClient(payment);
        return tellFortune();
    }

    private void takePaymentFromClient(float payment) {
        hasClient = true;
        if (isHappyWithPayment(payment)) {
            hasCrossedPalmWithSilver = true;
        }
    }

    private static boolean isHappyWithPayment(final float payment) {
        return payment >= 10.0;
    }

    private String tellFortune() {
        String fortune;
        if (!hasClient) return "You need to get a client";
        if (hasCrossedPalmWithSilver) {
            fortune = returnRandomFortune();
        } else {
            fortune = returnFortuneToGetMoreMoney();
        }
        resetClient();
        return fortune;
    }
    
    private String returnRandomFortune() {
        int randomNumberForFortune = random.nextInt(fortunes.size()) + 1;
        return fortunes.get(randomNumberForFortune);
    }

    private String returnFortuneToGetMoreMoney() {
        int randomNumberForMoreMoney = random.nextInt(fortunesThatCollectMoreMoney.size()) + 1;
        return fortunesThatCollectMoreMoney.get(randomNumberForMoreMoney);
    }

    private void resetClient() {
        hasClient = false;
        hasCrossedPalmWithSilver = false;
    }

    public static void main(String[] args) {
        FortuneTellerDeCoupled fortuneTeller = new FortuneTellerDeCoupled();
        Random r = new Random();
        IntStream.rangeClosed(1, 10)
                .forEach(i -> {
                    float payment = r.nextFloat() * 20F;
                    System.out.println(payment);
                    System.out.println(fortuneTeller.welcomeClient(payment));
                });
    }
}
