package com.ruppyrup.episode13.spring.annotations.service;


import com.ruppyrup.solid.DIP.spring.rrframework.rrannotations.RRComponent;

@RRComponent(profile = "AP")
public class ApuravChauhanMessageProvider implements IMessageProvider {

    @Override
    public String getMessage() {
        return "Apurav Chauhan";
    }
}
