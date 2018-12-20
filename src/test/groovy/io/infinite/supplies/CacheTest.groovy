package io.infinite.supplies

import io.infinite.supplies.ast.cache.EagerMap
import org.junit.Test

class CacheTest extends TestBase {

    EagerMap eagerMap = new EagerMap()

    String nullString = eagerMap.useCache('nullString', {
        null
    }, this)
    String string = eagerMap.useCache('string', {
        'test string2'
    }, this)
    String uuid = eagerMap.useCache('uuid', {
        UUID.randomUUID().toString()
    }, this)

    @Test
    void test() {
        def staticInitTestSource = getTestObjectFromResource("tests", "StaticInitTestSource.groovy")
        assert staticInitTestSource.nullString == null
        assert staticInitTestSource.string == "test string"
        def staticInitTestSource2 = getTestObjectFromResource("tests", "StaticInitTestSource.groovy")
        assert staticInitTestSource.uuid == staticInitTestSource2.uuid
        assert staticInitTestSource.toString() == staticInitTestSource2.toString()
        Thread thread1 = new Thread({
            def staticInitTestSourceThread = getTestObjectFromResource("tests", "StaticInitTestSource.groovy")
            assert staticInitTestSourceThread.uuid == staticInitTestSource2.uuid
        })
        thread1.start()
        thread1.join()

    }

}
