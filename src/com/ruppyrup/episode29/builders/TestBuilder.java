package com.ruppyrup.episode29.builders;

public class TestBuilder {
    public static void main(String[] args) {
        Customer bob = Customer.builderOf("bob", "smith")
                .withAddress("7 Beachamp Road")
                .withAge(68)
                .withPhoneNumber("7049653")
                .withSex(Customer.Sex.MALE)
                .build();

        System.out.println(bob);

        Patient sam = Patient.builderOf("Sam", "Waldron")
                .with(builder -> {
                    builder.age = 33;
                    builder.address = "59 Barns Lane";
                    builder.phoneNumber = "01185555555";
                    builder.sex = Patient.Sex.FEMALE;
                }).build();

        System.out.println(sam);
    }
}
