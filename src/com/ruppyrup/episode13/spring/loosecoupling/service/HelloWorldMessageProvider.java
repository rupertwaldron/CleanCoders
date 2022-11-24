package com.ruppyrup.episode13.spring.loosecoupling.service;

public class HelloWorldMessageProvider implements IMessageProvider {

    @Override
    public String getMessage() {
        return "Hello World!";
    }
}
