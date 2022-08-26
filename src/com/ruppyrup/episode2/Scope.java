package com.ruppyrup.episode2;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class Scope {

    List<SocketInstruction> socketInstructions = new ArrayList<>(); // variable with a class scope and long name

    public void serve(Socket s) {
        try {
            tryProcessInstructions(s); // private method small scope - large name
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            stop(); // public method large scope -> small name
            close(); // public method large scope -> small name
            closeEnclosingServiceInSeparateThreats(); // private method small scope - large name
        }
    }

    private void tryProcessInstructions(final Socket s) {
        for(SocketInstruction i : socketInstructions) { // i is a variable with a very small scope and a short name
            i.process(s);
        }
    }

    private static class SocketInstruction { // smaller scope - longer name
        public void process(final Socket s) {
        }
    }

    private void closeEnclosingServiceInSeparateThreats() {}

    public void stop() {}

    public void close() {}
}
