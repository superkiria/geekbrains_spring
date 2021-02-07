package ru.motrichkin.rest;

public class ErrorMessage {

    private String errorCode;
    private String message;
    private String localizedMessage;
    private StackTraceElement[] stackTrace;

    public ErrorMessage() {
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLocalizedMessage() {
        return localizedMessage;
    }

    public void setLocalizedMessage(String localizedMessage) {
        this.localizedMessage = localizedMessage;
    }

    public StackTraceElement[] getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(StackTraceElement[] stackTrace) {
        this.stackTrace = stackTrace;
    }

    public static Builder builder() {
        return new ErrorMessage().new Builder();
    }

    public class Builder {

        public Builder setErrorCode(String errorCode) {
            ErrorMessage.this.errorCode = errorCode;
            return this;
        }

        public Builder setMessage(String message) {
            ErrorMessage.this.message = message;
            return this;
        }

        public Builder setLocalizedMessage(String localizedMessage) {
            ErrorMessage.this.localizedMessage = localizedMessage;
            return this;
        }

        public Builder setStackTrace(StackTraceElement[] stackTrace) {
            ErrorMessage.this.stackTrace = stackTrace;
            return this;
        }

        public ErrorMessage build() {
            return ErrorMessage.this;
        }

    }
}
