package io.infinite.supplies.ast.cache

import io.infinite.supplies.ast.other.ASTUtils

import java.util.concurrent.ConcurrentHashMap

class EagerMap extends ConcurrentHashMap {

    Object useCache(String key, Closure closure, Object owner) {
        new ASTUtils().ensureClosureEquivalency(closure, owner)
        if (!containsKey(key)) {
            Object result = closure.call()
            if (result != null) {
                put(key, result)
            }
            return result
        } else {
            return get(key)
        }
    }

}
