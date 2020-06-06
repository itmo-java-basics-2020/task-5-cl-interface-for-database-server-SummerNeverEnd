package ru.andrey.kvstorage;

import ru.andrey.kvstorage.console.DatabaseCommand;
import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.DatabaseCommands;
import ru.andrey.kvstorage.console.ExecutionEnvironment;
import ru.andrey.kvstorage.exception.DatabaseException;

import java.util.Arrays;

public class DatabaseServer {

    private final ExecutionEnvironment env;

    public DatabaseServer(ExecutionEnvironment env) {
        this.env = env;
    }

    public static void main(String[] args) {

    }

    DatabaseCommandResult executeNextCommand(String commandText) {
        if (commandText == null) {
            return DatabaseCommandResult.error("Command wasn't passed");
        }

        String[] args = commandText.split(" ");
        if (args.length <= 1) {
            return DatabaseCommandResult.error("Arguments wasn't passed");
        }

        try {
            DatabaseCommand command = DatabaseCommands.valueOf(args[0]).getCommand(this.env,
                    Arrays.copyOfRange(args, 1, args.length));
            return command.execute();
        } catch (Exception e) {
            return DatabaseCommandResult.error(e.getMessage());
        }
    }
}