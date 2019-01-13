package io.infinite.supplies.other


import java.util.concurrent.ConcurrentHashMap

class ThreadLocal<Type> {

    public static ConcurrentHashMap<Thread, Type> objectsByThread = new ConcurrentHashMap<Thread, Type>()

    void set(Type object) {
        objectsByThread.put(Thread.currentThread(), object)
    }

    Type get() {
        objectsByThread.get(Thread.currentThread())
    }

    void clear() {
        objectsByThread.clear()
    }

}