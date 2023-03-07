package com.ruppyrup.episode23.stub.tests;

import com.ruppyrup.episode23.stub.login.LoginInteractor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.ruppyrup.episode23.stub.login.LoginInteractor.LOGIN_FAILURE_MESSAGE;
import static com.ruppyrup.episode23.stub.login.LoginInteractor.LOGIN_SUCESS_MESSAGE;
import static org.junit.jupiter.api.Assertions.*;

class LoginInteractorTest {

    private LoginInteractor loginInteractor;

    @BeforeEach
    void setup() {
        loginInteractor = new LoginInteractor();
    }

    @Test
    void successMessageReceivedForValidUser() {
        loginInteractor.setAuthorizer(new AcceptingAuthorizerStub());
        String loginResponse = loginInteractor.login("bob", "anypassword");
        Assertions.assertThat(loginResponse).isEqualTo(LOGIN_SUCESS_MESSAGE);
    }

    @Test
    void failureMessageReceivedForValidUser() {
        loginInteractor.setAuthorizer(new RejectingAuthorizerStub());
        String loginResponse = loginInteractor.login("bob", "anypassword");
        Assertions.assertThat(loginResponse).isEqualTo(LOGIN_FAILURE_MESSAGE);
    }

}