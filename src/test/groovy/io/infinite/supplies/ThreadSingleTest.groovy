package io.infinite.supplies

import io.infinite.supplies.other.ThreadSingle
import org.junit.Test

class ThreadSingleTest {

    @Test
    void runTest() {
        Thread.start {
            (0..1000000).each {
                new ThreadSingle<String>().set("abcd")
                new ThreadSingle<Integer>().set(123)
                assert new ThreadSingle<String>().get() == "abcd"
                assert new ThreadSingle<Integer>().get() == 123
                new ThreadSingle<Integer>().clear()
                assert new ThreadSingle<Integer>().get() == null
            }
        }.join()
        Thread.start {
            assert new ThreadSingle<String>().get() == null
            assert new ThreadSingle<Integer>().get() == null
        }.join()
    }

}
