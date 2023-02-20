package com.ruppyrup.episode12.traits.abstractclass;

import org.junit.jupiter.api.Test;

public class TestService extends AbstractTest {

    // I get all this stuff if I use inheritance
    @Test
    void setup() {
        logger.info("I have a logger");
        connection.connect();
    }
}
