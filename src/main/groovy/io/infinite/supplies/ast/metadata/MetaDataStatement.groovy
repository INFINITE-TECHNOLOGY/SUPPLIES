package io.infinite.supplies.ast.metadata

import groovy.transform.ToString
import io.infinite.supplies.ast.other.ASTUtils
import org.codehaus.groovy.ast.stmt.Statement

@ToString(includeNames = true, includeFields = true, includeSuper = true)
class MetaDataStatement extends MetaDataASTNode {

    String statementClassName
    String sourceNodeName

    MetaDataStatement(
            String statementClassName,
            String origCodeString,
            Integer columnNumber,
            Integer lastColumnNumber,
            Integer lineNumber,
            Integer lastLineNumber,
            String sourceNodeName
    ) {
        this.statementClassName = statementClassName
        this.restoredScriptCode = origCodeString
        this.columnNumber = columnNumber
        this.lastColumnNumber = lastColumnNumber
        this.lineNumber = lineNumber
        this.lastLineNumber = lastLineNumber
        this.sourceNodeName = sourceNodeName
    }

    MetaDataStatement(Statement statement) {
        MetaDataStatement(
                ((Statement) statement).getClass().getSimpleName(),
                new ASTUtils().codeString(statement),
                ((Statement) statement).getColumnNumber(),
                ((Statement) statement).getLastColumnNumber(),
                ((Statement) statement).getLineNumber(),
                ((Statement) statement).getLastLineNumber(),
                null
        )
    }

}
