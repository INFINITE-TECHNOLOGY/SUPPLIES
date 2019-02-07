package io.infinite.supplies.conf

import groovy.transform.CompileStatic
import io.infinite.supplies.ast.exceptions.RuntimeException

@CompileStatic
class ResourceLookupAbstract {

    String moduleName
    String resourceName
    Boolean proceedSearch = true

    ResourceLookupAbstract(String moduleName, String resourceName, Boolean proceedSearch) {
        if (resourceName == null || resourceName == "") {
            throw new RuntimeException("Resource name can not be null or empty: " + resourceName == "" ? "empty" : "null")
        }
        if (moduleName == null || moduleName == "") {
            throw new RuntimeException("Module name can not be null or empty: " + moduleName == "" ? "empty" : "null")
        }
        this.moduleName = moduleName
        this.resourceName = resourceName
        this.proceedSearch = proceedSearch
    }

    ResourceLookupAbstract(String moduleName, String resourceName) {
        this.moduleName = moduleName
        this.resourceName = resourceName
    }

    void report(String msg) {
        println(moduleName.padRight(16) + ": " + Thread.currentThread().getName() + ": " + msg)
    }

}
