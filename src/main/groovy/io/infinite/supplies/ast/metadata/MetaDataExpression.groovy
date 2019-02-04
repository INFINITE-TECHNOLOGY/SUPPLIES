package io.infinite.supplies.ast.metadata

import groovy.transform.ToString
import org.codehaus.groovy.ast.MethodNode
import org.codehaus.groovy.ast.expr.Expression

@ToString(includeNames = true, includeFields = true, includeSuper = true)
class MetaDataExpression extends MetaDataASTNode {

    String expressionClassName
    String restoredScriptCode

    MetaDataExpression(Expression expression, MethodNode methodNode, String restoredScriptCode) {
        initUsingAstNode(expression)
        additionalExpressionInit(expression.getClass().getSimpleName(), restoredScriptCode)
        initMethodMetaData(methodNode.name, methodNode.getDeclaringClass().getName())
    }

    void additionalExpressionInit(
            String expressionClassName,
            String restoredScriptCode
    ) {
        this.expressionClassName = expressionClassName
        this.restoredScriptCode = restoredScriptCode
    }

    MetaDataExpression(
            String expressionClassName,
            String restoredScriptCode,
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
        additionalExpressionInit(
                expressionClassName,
                restoredScriptCode
        )
    }

}
