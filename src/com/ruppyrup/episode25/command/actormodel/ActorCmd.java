package com.ruppyrup.episode25.command.actormodel;

import java.util.*;
import java.util.List;

@FunctionalInterface
public interface ActorCmd {
    void execute();
}

class ActorModelTest {

    private static Deque<ActorCmd> actorCmds = new LinkedList<>();
    private static List<String> pressedKeys = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        ActorCmd lightOn1 = new LightOn(1);
        ActorCmd lightOn2 = new LightOn(2);
        ActorCmd lightOn3 = new LightOn(3);
        ActorCmd lightOn4 = new LightOn(4);

        ActorCmd button1 = new ButtonActorCmd("1", lightOn1);
        ActorCmd button2 = new ButtonActorCmd("2", lightOn2);
        ActorCmd button3 = new ButtonActorCmd("3", lightOn3);
        ActorCmd button4 = new ButtonActorCmd("4", lightOn4);

        actorCmds.add(button1);
        actorCmds.add(button2);
        actorCmds.add(button3);
        actorCmds.add(button4);

        Thread listener = new Listen();
        listener.start();

        while (!actorCmds.isEmpty()) {
            ActorCmd cmd = actorCmds.pop();
            cmd.execute();
        }

        listener.join();
    }

    static class ButtonActorCmd implements ActorCmd {
        private final ActorCmd onTrigger;
        private final String trigger;

        ButtonActorCmd(String trigger, ActorCmd onTrigger) {
            this.onTrigger = onTrigger;
            this.trigger = trigger;
        }

        @Override
        public void execute() {
            actorCmds.add(triggerReceived() ? onTrigger : this); // if it isn't pressed it goes back on the stack
        }

        private boolean triggerReceived() {
            return pressedKeys.contains(trigger);
        }
    }

    static class LightOn implements ActorCmd {

        private final int lightNo;

        LightOn(int lightNo) {
            this.lightNo = lightNo;
        }

        @Override
        public void execute() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Light " + lightNo + " is on!!");
        }
    }

    static class Listen extends Thread {
        Scanner scanner = new Scanner(System.in);
        @Override
        public void run() {
            while(!actorCmds.isEmpty()) {
                String next = scanner.next();
                System.out.println("Listener has received " + next);
                pressedKeys.add(next);
            }
        }
    }
}
