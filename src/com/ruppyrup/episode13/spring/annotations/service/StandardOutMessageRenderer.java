package com.ruppyrup.episode13.spring.annotations.service;

import com.ruppyrup.episode13.spring.rrframework.rrannotations.RRAutoWire;
import com.ruppyrup.episode13.spring.rrframework.rrannotations.RRComponent;

@RRComponent
public class StandardOutMessageRenderer implements IMessageRenderer {
    @RRAutoWire(profile = "hello")
    private IMessageProvider messageProvider;

    @Override
    public void render() {
        if (messageProvider == null) {
            throw new RuntimeException("You must set the property messageProvider of class:" + StandardOutMessageRenderer.class.getName());
        }

        System.out.println(messageProvider.getMessage());
    }

    @Override
    public void setMessageProvider(IMessageProvider provider) {
        this.messageProvider = provider;
    }

    @Override
    public IMessageProvider getMessageProvider() {
        return this.messageProvider;
    }
}
