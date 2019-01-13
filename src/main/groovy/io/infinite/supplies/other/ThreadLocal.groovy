package io.infinite.supplies.other

import io.infinite.supplies.ast.cache.Cache

import java.util.concurrent.ConcurrentHashMap

class ThreadLocal {

    @Cache
    ConcurrentHashMap<Thread, Object> objectsByThread = new ConcurrentHashMap<Thread, Object>()

    void set(Object object) {
        objectsByThread.put(Thread.currentThread(), object)
    }

    Object get() {
        objectsByThread.get(Thread.currentThread())
    }

    void clear() {
        objectsByThread.clear()
    }

}
