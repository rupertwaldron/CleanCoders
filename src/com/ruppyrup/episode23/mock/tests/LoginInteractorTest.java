package com.ruppyrup.episode23.mock.tests;


import com.ruppyrup.episode23.mock.authorizer.Authorizer;
import com.ruppyrup.episode23.mock.entities.UserStub;
import com.ruppyrup.episode23.mock.usecases.*;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


public class LoginInteractorTest {
    private LoginInteractorImpl interactor;
    private AuthorizerSpy authorizerSpy;
    private UserGatewaySpy gatewaySpy;
    private LoginPresenterSpy presenterSpy;

    @BeforeEach
    public void setupInteractor() {
        interactor = new LoginInteractorImpl();
        presenterSpy = new LoginPresenterSpy();
        interactor.setPresenter(presenterSpy);
    }

    @Nested
    @DisplayName("ValidLoginTests")
    class ValidLoginTests {
        @BeforeEach
        public void setupValidLogin() {
            authorizerSpy = new AcceptingAuthorizerSpy();
            gatewaySpy = new UserGatewaySpy();
            interactor.setAuthorizer(authorizerSpy);
            interactor.setUserGateway(gatewaySpy);
        }

        @Test
        public void normalLogin() throws Exception {
            LoginRequest request = new LoginRequest();
            request.username = "username";
            request.password = "password";

            interactor.login(request);

            assertThat(authorizerSpy.getUsername()).isEqualTo("username");
            assertThat(authorizerSpy.getPassword()).isEqualTo("password");

            Assertions.assertThat(gatewaySpy.getRequestedId()).isEqualTo(AcceptingAuthorizerSpy.STUB_ID);

            LoginResponse response = presenterSpy.getInvokedResponse();
            assertThat(response.name).isEqualTo(UserStub.STUB_NAME);
            assertThat(response.lastLoginTime).isEqualTo(UserStub.STUB_TIME);
            assertThat(response.loginCount).isEqualTo(UserStub.STUB_LOGIN_COUNT);
        }
    }

    @Nested
    @DisplayName("Invalid Login Tests")
    class InvalidLoginTests {
        Authorizer authorizer;
        UserGateway userGateway;

        @BeforeEach
        public void setupInvalidLogin() {
            authorizer = new RejectingAuthorizerStub();
            userGateway = new UserGatewayStub();
            interactor.setAuthorizer(authorizer);
            interactor.setUserGateway(userGateway);
        }

        @Test
        public void whenLoginFails_loginFailureMessageIsPresented() throws Exception {
            LoginRequest request = new LoginRequest();
            request.username = "bad_username";
            request.password = "bad_password";

            interactor.login(request);

            LoginResponse invokedResponse = presenterSpy.getInvokedResponse();
            assertThat(invokedResponse.message).isEqualTo(LoginInteractor.LOGIN_FAILURE_MESSAGE);
        }
    }

    @Nested
    @DisplayName("RepeatedLoginFailureTests")
    class RepeatedLoginFailureTests {

        private UserGatewayStub userGatewayStub;

        @BeforeEach
        public void setupLoginFailures() {
            authorizerSpy = new RejectingAuthorizerSpy();
            userGatewayStub = new UserGatewayStub();
            interactor.setAuthorizer(authorizerSpy);
            interactor.setUserGateway(userGatewayStub);
        }

        @Test
        public void threeStrikesAndYouAreOut() throws Exception {
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
    }

    @Nested
    @DisplayName("MockedRepeatedLoginFailureTests")
    class MockedVersionOfRepeatedLoginFailure {
        private UserGatewayStub userGatewayStub;
        private RepeatedLoginAuthorizerMock authorizerMock;

        @BeforeEach
        public void setupLoginFailures() {
            authorizerMock = new RepeatedLoginAuthorizerMock();
            userGatewayStub = new UserGatewayStub();
            interactor.setAuthorizer(authorizerMock);
            interactor.setUserGateway(userGatewayStub);
        }

        @Test
        public void threeStrikesAndYouAreOut() throws Exception {
            LoginRequest request = new LoginRequest();
            request.username = "username";
            request.password = "bad_password";

            interactor.login(request);
            interactor.login(request);
            interactor.login(request);
            assertThat(authorizerMock.verifyHeldOnThirdAttempt("username")).isTrue();
        }
    }

    @Nested
    @DisplayName("FakedLoginTests")
    class LoginTestsUsingFakeAuthorizer {
        private Authorizer authorizer;
        private UserGateway userGateway;

        @BeforeEach
        public void setupForFake() {
            authorizer = new FakeAuthorizer();
            userGateway = new UserGatewayStub();
            interactor.setAuthorizer(authorizer);
            interactor.setUserGateway(userGateway);
        }

        @Test
        public void normalLogin() {
            LoginRequest request = new LoginRequest();
            request.username = "gooduser";
            request.password = "goodpassword";

            interactor.login(request);

            LoginResponse response = presenterSpy.getInvokedResponse();
            assertThat(response.name).isEqualTo(UserStub.STUB_NAME);
            assertThat(response.lastLoginTime).isEqualTo(UserStub.STUB_TIME);
            assertThat(response.loginCount).isEqualTo(UserStub.STUB_LOGIN_COUNT);
        }

        @Test
        public void whenLoginFails_loginFailureMessageIsPresented() {
            LoginRequest request = new LoginRequest();
            request.username = "bad_username";
            request.password = "bad_password";

            interactor.login(request);

            LoginResponse invokedResponse = presenterSpy.getInvokedResponse();
            assertThat(invokedResponse.message).isEqualTo(LoginInteractor.LOGIN_FAILURE_MESSAGE);
        }

    }
}



