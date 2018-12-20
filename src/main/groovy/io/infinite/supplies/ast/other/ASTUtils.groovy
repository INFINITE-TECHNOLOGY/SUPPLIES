package io.infinite.supplies.ast.other

import groovy.inspect.swingui.AstNodeToScriptVisitor
import io.infinite.supplies.ast.metadata.MetaDataExpression
import io.infinite.supplies.ast.metadata.MetaDataMethodNode
import io.infinite.supplies.ast.metadata.MetaDataStatement
import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.ast.MethodNode
import org.codehaus.groovy.ast.expr.Expression
import org.codehaus.groovy.ast.stmt.Statement

import java.lang.reflect.Field

class ASTUtils {

    String codeString(ASTNode iAstNode) {
        StringWriter stringWriter = new StringWriter()
        iAstNode.visit(new AstNodeToScriptVisitor(stringWriter))
        return stringWriter.getBuffer().toString()
    }

    void ensureClosureEquivalency(Closure closure, Object owner) {
        Field thisObjectField = Closure.getDeclaredField('thisObject')
        Field ownerField = Closure.getDeclaredField('owner')
        thisObjectField.setAccessible(true)
        ownerField.setAccessible(true)
        thisObjectField.set(closure, owner)
        ownerField.set(closure, owner)
        closure.setDelegate(owner)
        closure.setResolveStrategy(Closure.DELEGATE_ONLY)
        ownerField.setAccessible(false)
        thisObjectField.setAccessible(false)
    }

    String prepareExceptionMessage(ASTNode astNode) {
        String message
        switch (astNode) {
            case MethodNode:
                message = new MetaDataMethodNode(astNode as MethodNode).toString()
                break
            case Statement:
                message = new MetaDataStatement(astNode as Statement).toString()
                break
            case Expression:
                message = new MetaDataExpression(astNode as Expression).toString()
                break
            default:
                message = astNode.toString()
        }
        return message
    }

}
