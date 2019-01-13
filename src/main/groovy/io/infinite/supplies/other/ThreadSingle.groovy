package io.infinite.supplies.other

import io.infinite.supplies.ast.cache.Cache

import java.lang.reflect.ParameterizedType
import java.util.concurrent.ConcurrentHashMap

class ThreadSingle<Type> {

    final Class<Type> typeClass = ((Class<Type>) ((ParameterizedType) (getClass()
            .getGenericSuperclass())).getActualTypeArguments()[0])

    @Cache
    ConcurrentHashMap<Class, ThreadLocal> threadLocalsByClass = new ConcurrentHashMap<Class, ThreadLocal>()

    Type get() {
        return threadLocalsByClass.get(typeClass)?.get() as Type
    }

    void set(Type value) {
        ThreadLocal threadLocal = new ThreadLocal()
        threadLocal.set(value)
        threadLocalsByClass.put(typeClass, threadLocal)
    }

    void clear() {
        threadLocalsByClass.remove(typeClass)
    }

}