package tests

import groovy.transform.ToString
import io.infinite.supplies.ast.cache.Static

@ToString(includeFields = true, includeNames = true)
class StaticTestBasic {

    @Static
    final String nullString

    @Static
    final String string = "test string"

    @Static
    final String uuid = UUID.randomUUID().toString()

}
