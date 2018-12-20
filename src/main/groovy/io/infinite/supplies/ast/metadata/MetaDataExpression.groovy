package io.infinite.supplies.ast.metadata

import groovy.transform.ToString
import io.infinite.supplies.ast.other.ASTUtils
import org.codehaus.groovy.ast.expr.Expression

@ToString(includeNames = true, includeFields = true, includeSuper = true)
class MetaDataExpression extends MetaDataASTNode {

    String expressionClassName
    String sourceNodeName

    MetaDataExpression(
        String expressionClassName,
        String origCodeString,
        Integer columnNumber,
        Integer lastColumnNumber,
        Integer lineNumber,
        Integer lastLineNumber,
        String sourceNodeName
    ) {
        this.expressionClassName = expressionClassName
        this.restoredScriptCode = origCodeString
        this.columnNumber = columnNumber
        this.lastColumnNumber = lastColumnNumber
        this.lineNumber = lineNumber
        this.lastLineNumber = lastLineNumber
        this.sourceNodeName = sourceNodeName
    }

    MetaDataExpression(Expression expression) {
        MetaDataExpression(
                ((Expression) expression).getClass().getSimpleName(),
                new ASTUtils().codeString(expression),
                ((Expression) expression).getColumnNumber(),
                ((Expression) expression).getLastColumnNumber(),
                ((Expression) expression).getLineNumber(),
                ((Expression) expression).getLastLineNumber(),
                null
        )
    }

}
