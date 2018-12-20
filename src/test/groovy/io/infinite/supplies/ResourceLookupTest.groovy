package io.infinite.supplies

import io.infinite.supplies.conf.ResourceLookupThread
import org.junit.Test

class ResourceLookupTest {

    @Test
    void test() {
        assert new ResourceLookupThread("supplies", "dummy/dummy.resource", true).getResourceAsFile() != null
    }

}
