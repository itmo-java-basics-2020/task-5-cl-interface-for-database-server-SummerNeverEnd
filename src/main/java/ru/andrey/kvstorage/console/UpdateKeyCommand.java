package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.console.DatabaseCommand;
import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.ExecutionEnvironment;
import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

public class UpdateKey implements DatabaseCommand {

    private static final String RESULT = "Value was created.";
    private static final String ALREADY_EXISTS = "Table already exists";
    private static final String DOESNT_EXISTS = "Table doesn't exists";

    private ExecutionEnvironment executionEnvironment;
    private String databaseName;
    private String tableName;
    private String objectKey;
    private String objectValue;

    public UpdateKey(ExecutionEnvironment executionEnvironment, String databaseName, String tableName, String objectKey, String objectValue) {
        this.executionEnvironment = executionEnvironment;
        this.databaseName = databaseName;
        this.tableName = tableName;
        this.objectKey = objectKey;
        this.objectValue = objectValue;
    }

    @Override
    public DatabaseCommandResult execute() throws DatabaseException {
        if (executionEnvironment.getDatabase(databaseName).isEmpty()) {
            return DatabaseCommandResult.error(DOESNT_EXISTS);
        }
        Database db = executionEnvironment.getDatabase(databaseName).get();
        try {
            db.write(tableName, objectKey, objectValue);
            return DatabaseCommandResult.success(RESULT);
        } catch (DatabaseException e) {
            return DatabaseCommandResult.error(ALREADY_EXISTS);
        }

    }
}