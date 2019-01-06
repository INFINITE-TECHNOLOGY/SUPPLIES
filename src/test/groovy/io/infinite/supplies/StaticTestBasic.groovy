package io.infinite.supplies

import io.infinite.supplies.ast.cache.EagerMap
import org.junit.Test

class StaticTestBasic extends TestBase {

    EagerMap eagerMap = new EagerMap()

    String nullString = eagerMap.passThrough('nullString', {
        null
    }, this)
    String string = eagerMap.passThrough('string', {
        'test string2'
    }, this)
    String uuid = eagerMap.passThrough('uuid', {
        UUID.randomUUID().toString()
    }, this)

    @Test
    void test() {
        def staticInitTestSource = getTestObjectFromResource("tests", "StaticTestCompiling.groovy")
        assert staticInitTestSource.nullString == null
        assert staticInitTestSource.string == "test string"
        def staticInitTestSource2 = getTestObjectFromResource("tests", "StaticTestCompiling.groovy")
        assert staticInitTestSource.uuid == staticInitTestSource2.uuid
        assert staticInitTestSource.toString() == staticInitTestSource2.toString()
        Thread thread1 = new Thread({
            def staticInitTestSourceThread = getTestObjectFromResource("tests", "StaticTestCompiling.groovy")
            assert staticInitTestSourceThread.uuid == staticInitTestSource2.uuid
        })
        thread1.start()
        thread1.join()

    }

}
