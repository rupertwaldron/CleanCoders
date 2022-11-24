package com.ruppyrup.episode13.spring.loosecoupling.service;

public interface IMessageRenderer {
    void render();

    // setter used by spring for injection
    void setMessageProvider(IMessageProvider provider);

    IMessageProvider getMessageProvider();
}
