package ru.andrey.kvstorage.console;

import java.util.Optional;

public interface DatabaseCommandResult {

    Optional<String> getResult();

    DatabaseCommandStatus getStatus();

    boolean isSuccess();

    String getErrorMessage();

    enum DatabaseCommandStatus {
        SUCCESS, FAILED
    }

    static DatabaseCommandResult success(String result) {
        return new DatabaseCommandResultImplementation(DatabaseCommandStatus.SUCCESS, result, null);
    }

    static DatabaseCommandResult error(String message) {
        return new DatabaseCommandResultImplementation(DatabaseCommandStatus.FAILED, null, message);
    }

    class DatabaseCommandResultImplementation implements DatabaseCommandResult {

        private final String result;
        private final String errorMessage;
        private final DatabaseCommandStatus status;

        private DatabaseCommandResultImplementation(DatabaseCommandStatus status, String result, String errorMessage) {
            this.status = status;
            this.result = result;
            this.errorMessage = errorMessage;
        }

        @Override
        public Optional<String> getResult() {
            return Optional.ofNullable(result);
        }

        @Override
        public DatabaseCommandStatus getStatus() {
            return status;
        }

        @Override
        public boolean isSuccess() {
            return status.equals(DatabaseCommandStatus.SUCCESS);
        }

        @Override
        public String getErrorMessage() {
            return errorMessage;
        }
    }

}