package ru.andrey.kvstorage.console.commands;

import ru.andrey.kvstorage.exception.DatabaseException;

public class CreateDatabaseCommand implements DatabaseCommand {

    private final String databaseName;
    private final ExecutionEnvironment environment;

    public CreateDatabaseCommand(ExecutionEnvironment environment, String databaseName) {
        this.environment = environment;
        this.databaseName = databaseName;
    }

    @Override
    public DatabaseCommandResult execute() throws DatabaseException {
        environment.addDatabase(null);
        return DatabaseCommandResult.success(String.format("Database %s was created successfully", databaseName));
    }
}