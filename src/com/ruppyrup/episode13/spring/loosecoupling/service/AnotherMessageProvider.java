package com.ruppyrup.episode13.spring.loosecoupling.service;

public class AnotherMessageProvider implements IMessageProvider {

    @Override
    public String getMessage() {
        return "Another message";
    }
}
