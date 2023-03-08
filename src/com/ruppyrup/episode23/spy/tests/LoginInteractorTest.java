package com.ruppyrup.episode23.spy.tests;

import com.ruppyrup.episode23.spy.entities.UserStub;
import com.ruppyrup.episode23.spy.login.LoginInteractorImpl;
import com.ruppyrup.episode23.spy.login.LoginRequest;
import com.ruppyrup.episode23.spy.login.LoginResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Spy will give invalid login each time it is called
 */
public class LoginInteractorTest {
    private LoginInteractorImpl interactor;
    private AuthorizerSpy authorizerSpy;

    private LoginPresenterSpy loginPresenterSpy;

    @BeforeEach
    public void setupInteractor() {
        loginPresenterSpy = new LoginPresenterSpy();
        authorizerSpy = new RejectingAuthorizerSpy();
        interactor = new LoginInteractorImpl();
        interactor.setAuthorizer(authorizerSpy);
        interactor.setPresenter(loginPresenterSpy);
    }

    @Test
    public void threeStrikesAndYouAreOut() {
        LoginRequest request = new LoginRequest();
        request.username = "username";
        request.password = "bad_password";

        interactor.login(request);
        assertThat(authorizerSpy.heldUsername()).isNull();

        interactor.login(request);
        assertThat(authorizerSpy.heldUsername()).isNull();

        interactor.login(request);
        assertThat(authorizerSpy.heldUsername()).isEqualTo("username");
    }

    @Test
    public void normalLogin() throws Exception {
        interactor.setAuthorizer(new AcceptingAuthorizerStub());
        LoginRequest request = new LoginRequest();
        request.username = "gooduser";
        request.password = "goodpassword";

        interactor.login(request);

        LoginResponse response = loginPresenterSpy.getInvokedResponse();
        assertThat(response.message).isEqualTo("Welcome");
    }

}
