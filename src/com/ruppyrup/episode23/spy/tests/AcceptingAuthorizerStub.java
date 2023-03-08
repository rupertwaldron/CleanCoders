package com.ruppyrup.episode23.spy.tests;

import com.ruppyrup.episode23.spy.authorizer.UserID;

public class AcceptingAuthorizerStub extends AuthorizerSpy {
    @Override
    protected UserID makeUser() {
        return new UserID(1) ;
    }
}
