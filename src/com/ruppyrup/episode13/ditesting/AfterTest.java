package com.ruppyrup.episode13.ditesting;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AfterTest {


    @Test
    void transformInput() {
//        StringBuilder outputBuilder = new StringBuilder();
//        Output mockOutput = outputBuilder::append;
//        Input stubInput = () -> "test";

        Input stubInput = new StubInput("test");
        MockOutput mockOutput = new MockOutput();

        Transformer transformer = new UpperCaseTransformer();

        After after = new After(stubInput, mockOutput);

        after.transformInput(transformer);

        Assertions.assertEquals("TEST", mockOutput.getActual());
    }

    class StubInput implements Input {
        private String input;

        public StubInput(String input) {
            this.input = input;
        }

        @Override
        public String fetch() {
            return input;
        }
    }

    class MockOutput implements Output {
        private String output;

        @Override
        public void display(String output) {
            this.output = output;
        }

        public String getActual() {
            return output;
        }
    }
}


