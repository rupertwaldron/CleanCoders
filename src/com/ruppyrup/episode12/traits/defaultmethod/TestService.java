package com.ruppyrup.episode12.traits.defaultmethod;

import com.ruppyrup.episode12.traits.abstractclass.AbstractTest;
import com.ruppyrup.episode12.traits.abstractclass.MyConnection;
import org.junit.jupiter.api.Test;

public class TestService implements LoggingTrait, MyConnectionTrait {

    private MyConnection myConnection;
    @Test
    void setup() {
        logger.info("I have a logger");
        myConnection = refresh();
    }
}
