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
                statement.getClass().getSimpleName(),
                new ASTUtils().codeString(statement),
                statement.getColumnNumber(),
                statement.getLastColumnNumber(),
                statement.getLineNumber(),
                statement.getLastLineNumber(),
                null
        )
    }

}
