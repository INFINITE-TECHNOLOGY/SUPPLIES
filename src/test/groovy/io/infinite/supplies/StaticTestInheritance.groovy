package io.infinite.supplies


import org.junit.Test

class StaticTestInheritance extends TestBase {

    @Test
    void test() {
        runScriptFromResource("tests", "StaticTestInheritance.groovy")
    }

}
