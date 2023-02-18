package com.ruppyrup.episode32.proxy.commandexecutor;

public class CommandExecutorProxy implements CommandExecutor{

    private boolean isAdmin;
    private CommandExecutor executor;

    public CommandExecutorProxy(String user, String pwd) {
        if ("ruppyrup".equals(user) && "1234".equals(pwd)) isAdmin = true;
        executor = new CommandExecutorImpl();
    }

    @Override
    public void runCommand(String cmd) throws Exception {
        if (isAdmin) {
            executor.runCommand(cmd);
        } else {
            if (cmd.trim().startsWith("rm")) {
                throw new RuntimeException("rm command isn't allow for non-admin users");
            } else {
                executor.runCommand(cmd);
            }
        }
    }
}
