package com.ruppyrup.episode25.command.spatial;

@FunctionalInterface
public interface Command {
    void execute();
}

class Button {
    private final Command action;

    Button(Command action) {
        this.action = action;
    }

    void onClick() {
        action.execute();
    }
}

class TurnOnLight implements Command {

    @Override
    public void execute() {
        System.out.println("Light is on!!");
    }
}

class TestCommands {
    public static void main(String[] args) {
        Button button = new Button(() -> System.out.println("This action is from a lambda!"));

        Command turnOnLight = new TurnOnLight();

        Button button2 = new Button(turnOnLight);

        button.onClick();
        button2.onClick();
    }
}
