package com.ruppyrup.episode13.spring.annotations.service;
import com.ruppyrup.episode13.spring.rrframework.rrannotations.RRComponent;

@RRComponent(profile = "another")
public class AnotherMessageProvider implements IMessageProvider {

    @Override
    public String getMessage() {
        return "Another Message";
    }
}
