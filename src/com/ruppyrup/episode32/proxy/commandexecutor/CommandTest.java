package com.ruppyrup.episode32.proxy.commandexecutor;

public class CommandTest {

    public static void main(String[] args){
        CommandExecutor executor = new CommandExecutorProxy("ruppyrup", "wrong_pwd");
        try {
            executor.runCommand("ls -ltr");
            executor.runCommand(" rm -rf abc.pdf");
        } catch (Exception e) {
            System.out.println("Exception Message::"+e.getMessage());
        }

    }
}
