package tests

import groovy.transform.ToString
import io.infinite.supplies.ast.cache.Cache

@ToString(includeFields = true, includeNames = true)
class CacheTestBasic {

    @Cache
    final String nullString

    @Cache
    final String string = "test string"

    @Cache
    final String uuid = UUID.randomUUID().toString()

}
