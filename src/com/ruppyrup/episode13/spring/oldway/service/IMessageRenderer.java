package com.ruppyrup.episode13.spring.oldway.service;

public interface IMessageRenderer {
    public void render();
    public void setMessageProvider(IMessageProvider provider);
}
