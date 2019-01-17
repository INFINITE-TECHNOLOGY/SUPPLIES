package io.infinite.supplies.ast.exceptions

import io.infinite.supplies.ast.other.ASTUtils
import org.codehaus.groovy.ast.ASTNode

class RuntimeException extends Exception {

    RuntimeException(String var1) {
        super(var1)
    }

    RuntimeException(String var1, Exception exception) {
        super(var1, exception)
        this.setStackTrace([] as StackTraceElement[])
    }

    RuntimeException(ASTNode astNode, Exception exception) {
        super(new ASTUtils().prepareExceptionMessage(astNode), exception)
        this.setStackTrace([] as StackTraceElement[])
    }

}