package io.infinite.supplies.ast.exceptions

import io.infinite.supplies.ast.other.ASTUtils
import org.codehaus.groovy.ast.ASTNode

class CompileException extends Exception {

    CompileException(ASTNode astNode, Exception exception) {
        super(new ASTUtils().prepareExceptionMessage(astNode), exception)
        this.setStackTrace([] as StackTraceElement[])
    }

    CompileException(ASTNode astNode, String message) {
        super(message + "\n" + new ASTUtils().prepareExceptionMessage(astNode))
    }

}