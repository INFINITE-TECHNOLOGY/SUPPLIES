package io.infinite.supplies.ast.metadata

import groovy.transform.ToString
import org.codehaus.groovy.ast.MethodNode
import org.codehaus.groovy.ast.stmt.Statement

@ToString(includeNames = true, includeFields = true, includeSuper = true)
class MetaDataStatement extends MetaDataASTNode {

    String statementClassName

    MetaDataStatement(Statement statement, MethodNode methodNode) {
        initUsingAstNode(statement)
        additionalStatementInit(statement.getClass().getSimpleName())
        initMethodMetaData(methodNode.name, methodNode.getDeclaringClass().getName())
    }

    void additionalStatementInit(
            String statementClassName
    ) {
        this.statementClassName = statementClassName
    }

    MetaDataStatement(
            String statementClassName,
            Integer lineNumber,
            Integer lastLineNumber,
            Integer columnNumber,
            Integer lastColumnNumber,
            String methodName,
            String className
    ) {
        initUsingMetaDataFields(
                lineNumber,
                lastLineNumber,
                columnNumber,
                lastColumnNumber
        )
        initMethodMetaData(methodName, className)
        additionalStatementInit(
                statementClassName
        )
    }

}
