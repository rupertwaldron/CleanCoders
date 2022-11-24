package com.ruppyrup.episode13.ditesting;

import org.junit.jupiter.api.Test;

/**
 * Can't test manually!! Can't manually input to keyboard or read output
 */
class BeforeTest {

    @Test
    void upperCaseTextTest() {
        Before before = new Before();

        before.showInputInUppercase();
    }

}
