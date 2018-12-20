package io.infinite.supplies.ast.metadata

import groovy.transform.ToString
import org.codehaus.groovy.ast.MethodNode

@ToString(includeNames = true, includeFields = true, includeSuper = true)
class MetaDataMethodNode extends MetaDataASTNode {

    String methodName
    String className
    String classSimpleName
    String packageName

    MetaDataMethodNode(
            String methodClassName,
            String methodClassPackageName,
            String methodName,
            Integer columnNumber,
            Integer lastColumnNumber,
            Integer lineNumber,
            Integer lastLineNumber
    ) {
        this.packageName = methodClassPackageName
        this.classSimpleName = methodClassName
        this.className = methodClassPackageName + "." + methodClassName
        this.methodName = methodName
        this.columnNumber = columnNumber
        this.lastColumnNumber = lastColumnNumber
        this.lineNumber = lineNumber
        this.lastLineNumber = lastLineNumber
    }

    MetaDataMethodNode(MethodNode methodNode) {
        MetaDataMethodNode(
                ((MethodNode) methodNode).getDeclaringClass().getNameWithoutPackage(),
                ((MethodNode) methodNode).getDeclaringClass().getPackageName(),
                ((MethodNode) methodNode).getName(),
                ((MethodNode) methodNode).getColumnNumber(),
                ((MethodNode) methodNode).getLastColumnNumber(),
                ((MethodNode) methodNode).getLineNumber(),
                ((MethodNode) methodNode).getLastLineNumber()
        )
    }

}
