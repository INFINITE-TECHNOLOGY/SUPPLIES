package io.infinite.supplies.ast.exceptions

class RuntimeException extends Exception {

    RuntimeException(String var1) {
        super(var1)
    }

    RuntimeException(String var1, Exception exception) {
        super(var1, exception)
        this.setStackTrace([] as StackTraceElement[])
    }
}