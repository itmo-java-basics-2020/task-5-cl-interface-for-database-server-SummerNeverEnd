package ru.andrey.kvstorage.console.commands;

import ru.andrey.kvstorage.console.DatabaseCommand;
import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.ExecutionEnvironment;
import ru.andrey.kvstorage.exception.DatabaseException;

public class CreateDatabaseCommand implements DatabaseCommand {

    private final String databaseName;
    private final ExecutionEnvironment executionEnvironment;
    private static final String ALREADY_EXISTS = "Table already exists";

    public CreateDatabaseCommand(ExecutionEnvironment environment, String databaseName) {
        this.executionEnvironment = environment;
        this.databaseName = databaseName;
    }

    @Override
    public DatabaseCommandResult execute() throws DatabaseException {
        if (executionEnvironment.getDatabase(databaseName).isPresent()) {
            return DatabaseCommandResult.error(ALREADY_EXISTS);
        }
        executionEnvironment.addDatabase(null);
        return DatabaseCommandResult.success(String.format("Database %s was created successfully", databaseName));
    }
}