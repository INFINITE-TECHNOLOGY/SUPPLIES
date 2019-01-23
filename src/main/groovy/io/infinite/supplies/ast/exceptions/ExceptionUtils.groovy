package io.infinite.supplies.ast.exceptions

import org.codehaus.groovy.runtime.StackTraceUtils

class ExceptionUtils {

    String stacktrace(Throwable throwable) {
        Throwable throwableToProcess = getThrowableToProcess(throwable)
        StringWriter stringWriter = new StringWriter()
        throwableToProcess.printStackTrace(new PrintWriter(stringWriter))
        return stringWriter.toString()
    }

    String sanitizedStacktrace(Throwable throwable) {
        Throwable throwableToProcess = getThrowableToProcess(throwable)
        if (throwableToProcess.cause != null) {
            new StackTraceUtils().sanitizeRootCause(throwableToProcess)
        }
        return stacktrace(new StackTraceUtils().sanitize(throwableToProcess))
    }

    Throwable getThrowableToProcess(Throwable throwable) {
        Throwable throwableToProcess
        if (throwable.hasProperty("runtimeException")) {
            throwableToProcess = throwable.runtimeException
        } else {
            throwableToProcess = throwable
        }
        return throwableToProcess
    }

}
