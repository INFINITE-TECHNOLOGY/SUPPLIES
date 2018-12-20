package io.infinite.supplies

import org.junit.Test

class StaticInitTest extends TestBase {

    @Test
    void test() {
        def staticInitTestSource = getTestObjectFromResource("tests", "StaticInitTestSource.groovy")
        assert staticInitTestSource.nullString == null
        assert staticInitTestSource.string == "test string"
        def staticInitTestSource2 = getTestObjectFromResource("tests", "StaticInitTestSource.groovy")
        assert staticInitTestSource.uuid == staticInitTestSource2.uuid
        assert staticInitTestSource.toString() == staticInitTestSource2.toString()
    }

}
