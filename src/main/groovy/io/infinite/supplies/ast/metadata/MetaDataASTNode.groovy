package io.infinite.supplies.ast.metadata

import groovy.transform.ToString

@ToString(includeNames = true, includeFields = true)
abstract class MetaDataASTNode {

    String restoredScriptCode
    BigInteger lineNumber
    BigInteger columnNumber
    BigInteger lastLineNumber
    BigInteger lastColumnNumber

}
