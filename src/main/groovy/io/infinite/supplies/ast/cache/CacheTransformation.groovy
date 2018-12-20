package io.infinite.supplies.ast.cache

import io.infinite.supplies.ast.exceptions.CompileException
import jdk.internal.org.objectweb.asm.Opcodes
import org.codehaus.groovy.ast.*
import org.codehaus.groovy.ast.expr.ConstantExpression
import org.codehaus.groovy.ast.stmt.ExpressionStatement
import org.codehaus.groovy.ast.tools.GeneralUtils
import org.codehaus.groovy.classgen.VariableScopeVisitor
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.AbstractASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation

@GroovyASTTransformation(
        phase = CompilePhase.SEMANTIC_ANALYSIS
)
class CacheTransformation extends AbstractASTTransformation {

    AnnotationNode annotationNode

    static {
        ClassNode.getMetaClass().staticMapDeclared = null
    }

    void visit(ASTNode[] iAstNodeArray, SourceUnit iSourceUnit) {
        try {
            init(iAstNodeArray, iSourceUnit)
            annotationNode = iAstNodeArray[0] as AnnotationNode
            if (iAstNodeArray[1] instanceof FieldNode) {
                visitFieldNode(iAstNodeArray[1] as FieldNode)
            } else {
                throw new CompileException(iAstNodeArray[1], "Unsupported Annotated Node; Only FieldNode is supported.")
            }
        } catch (Exception exception) {
            throw new CompileException(iAstNodeArray[1], exception)
        }
    }

    void visitFieldNode(FieldNode fieldNode) {
        if (fieldNode.isStatic()) {
            throw new CompileException(fieldNode, "Field is already static itself.")
        }
        if (!fieldNode.isFinal()) {
            throw new CompileException(fieldNode, "Field must be final.")
        }
        declareStaticMapIfNeeded(fieldNode)
        setInitialValueExpression(fieldNode)
        sourceUnit.AST.classes.each {
            new VariableScopeVisitor(sourceUnit, true).visitClass(it)
        }
    }

    void setInitialValueExpression(FieldNode fieldNode) {
        fieldNode.setInitialValueExpression(
                GeneralUtils.callX(
                        GeneralUtils.varX(getMapVarName()),
                        "useCache",
                        GeneralUtils.args(
                                GeneralUtils.constX(fieldNode.getName()),
                                GeneralUtils.closureX(
                                        new ExpressionStatement(
                                                fieldNode.getInitialValueExpression() ?: new ConstantExpression(null)
                                        )
                                ),
                                GeneralUtils.varX("this")
                        )
                )
        )
    }

    void declareStaticMapIfNeeded(FieldNode fieldNode) {
        if (!fieldNode.getDeclaringClass().staticMapDeclared) {
            fieldNode.getDeclaringClass().addField(getMapVarName(),
                    Opcodes.ACC_FINAL | Opcodes.ACC_TRANSIENT | Opcodes.ACC_STATIC | Opcodes.ACC_PRIVATE,
                    ClassHelper.make(getMapImplementation()),
                    GeneralUtils.ctorX(ClassHelper.make(getMapImplementation()))
            )
            fieldNode.getDeclaringClass().staticMapDeclared = true
        }
    }

    Class getMapImplementation() {
        return EagerMap.class
    }

    String getMapVarName() {
        return "eagerMap"
    }

}
