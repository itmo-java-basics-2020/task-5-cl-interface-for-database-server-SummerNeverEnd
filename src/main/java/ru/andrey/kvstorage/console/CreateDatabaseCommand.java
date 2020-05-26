package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;

public class CreateDatabase implements DatabaseCommand {

    private final String databaseName;
    private final ExecutionEnvironment environment;

    public CreateDatabase(ExecutionEnvironment environment, String databaseName) {
        this.environment = environment;
        this.databaseName = databaseName;
    }

    @Override
    public DatabaseCommandResult execute() throws DatabaseException {
        environment.addDatabase(null);
        return DatabaseCommandResult.success(String.format("Database %s was created successfully", databaseName));
    }
}