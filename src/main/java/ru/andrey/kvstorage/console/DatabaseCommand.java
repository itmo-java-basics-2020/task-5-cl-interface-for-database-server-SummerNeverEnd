package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.exception.DatabaseException;

public interface DatabaseCommand {
    DatabaseCommandResult execute() throws DatabaseException;
}
