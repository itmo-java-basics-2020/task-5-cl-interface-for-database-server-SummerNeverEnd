package ru.andrey.kvstorage.console.commands;

import ru.andrey.kvstorage.console.DatabaseCommand;
import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.ExecutionEnvironment;
import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import java.util.Optional;

public class UpdateKeyCommand implements DatabaseCommand {

    private final String objectKey;
    private final String objectValue;
    private final String tableName;
    private final String databaseName;
    private final ExecutionEnvironment environment;

    public UpdateKeyCommand(ExecutionEnvironment environment, String databaseName, String tableName, String objectKey,
                            String objectValue) {
        this.databaseName = databaseName;
        this.tableName = tableName;
        this.objectKey = objectKey;
        this.objectValue = objectValue;
        this.environment = environment;
    }

    @Override
    public DatabaseCommandResult execute() throws DatabaseException {
        Optional<Database> database = environment.getDatabase(databaseName);
        if (database.isPresent()) {
            database.get().write(tableName, objectKey, objectValue);
            return DatabaseCommandResult.success(String.format("Key %s was successfully updated with value = %s",
                    objectKey, objectValue));
        } else {
            return DatabaseCommandResult.error("There is no database with this name");
        }
    }
}