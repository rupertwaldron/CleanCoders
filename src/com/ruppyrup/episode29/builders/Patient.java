package com.ruppyrup.episode29.builders;

import java.util.Optional;
import java.util.function.Consumer;

public class Patient {
    private final String firstName;
    private final String surname;
    private final Integer age;
    private final String address;
    private final String phoneNumber;
    private final Sex sex;

    private Patient(Builder builder) {
        this.firstName = builder.firstName;
        this.surname = builder.surname;
        this.age = builder.age;
        this.address = builder.address;
        this.phoneNumber = builder.phoneNumber;
        this.sex = builder.sex;
    }

    public static Builder builderOf(String firstName, String surname) {
        return new Builder(firstName, surname);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", sex=" + sex +
                '}';
    }

    static class Builder {
        private final String firstName;
        private final String surname;
        public Integer age;
        public String address;
        public String phoneNumber;
        public Sex sex;

        private Builder(String firstName, String surname) {
            this.firstName = firstName;
            this.surname = surname;
        }

        public Builder with(Consumer<Builder> consumer) {
            consumer.accept(this);
            return this;
        }


        public Patient build() {
            return new Patient(this);
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public Optional<Integer> getAge() {
        return Optional.ofNullable(age);
    }

    public Optional<String> getAddress() {
        return Optional.ofNullable(address);
    }

    public Optional<String> getPhoneNumber() {
        return Optional.ofNullable(phoneNumber);
    }

    public Optional<Sex> getSex() {
        return Optional.ofNullable(sex);
    }

    enum Sex {
        FEMALE,
        MALE
    }
}
