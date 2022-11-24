package com.ruppyrup.episode13.spring.annotations.service;


import com.ruppyrup.solid.DIP.spring.rrframework.rrannotations.RRComponent;

@RRComponent(profile = "hello")
public class HelloWorldMessageProvider implements IMessageProvider {

    @Override
    public String getMessage() {
        return "Hello World!";
    }
}
