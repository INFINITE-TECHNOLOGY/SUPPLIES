package io.infinite.supplies

import io.infinite.supplies.ast.exceptions.ExceptionUtils
import io.infinite.supplies.ast.exceptions.RuntimeException
import org.junit.jupiter.api.Test

class RuntimeExceptionTest {

    @Test
    void runTest() {
        try {
            throw new RuntimeException("Test exception")
        } catch (RuntimeException r) {
            println(new ExceptionUtils().stacktrace(r))
        }
    }

}
