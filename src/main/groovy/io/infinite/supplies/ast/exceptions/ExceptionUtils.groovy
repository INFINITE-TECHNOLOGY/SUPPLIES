package io.infinite.supplies.ast.exceptions

import org.codehaus.groovy.runtime.StackTraceUtils

class ExceptionUtils {

    String stacktrace(Throwable throwable) {
        StringWriter stringWriter = new StringWriter()
        throwable.printStackTrace(new PrintWriter(stringWriter))
        return stringWriter.toString()
    }

    String sanitizedStacktrace(Throwable throwable) {
        new StackTraceUtils().sanitizeRootCause(throwable)
        return stacktrace(new StackTraceUtils().sanitize(throwable))
    }

}
