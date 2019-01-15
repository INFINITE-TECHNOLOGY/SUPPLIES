package io.infinite.supplies.conf

import io.infinite.supplies.ast.exceptions.RuntimeException

class ResourceLookupWorkingDir {

    String moduleName
    String resourceName
    Boolean proceedSearch = true

    ResourceLookupWorkingDir(String moduleName, String resourceName, Boolean proceedSearch) {
        if (resourceName == null || resourceName == "") {
            throw new RuntimeException("Resource name can not be null or empty: " + resourceName == "" ? "empty" : "null")
        }
        this.moduleName = moduleName
        this.resourceName = resourceName
        this.proceedSearch = proceedSearch
    }

    ResourceLookupWorkingDir(String moduleName, String resourceName) {
        this.moduleName = moduleName
        this.resourceName = resourceName
    }

    String getResourceAsString() {
        report("Searching for ${getResourceName()} config in: " + getConfPath() + " (full path: ${new File(getConfPath()).getCanonicalPath()})")
        File file = new File(getConfPath())
        if (file.exists()) {
            report("Found: " + file.getCanonicalPath())
            return file.getText()
        } else {
            report("Not found.")
            return null
        }
    }

    String getWorkingDir() {
        return "./"
    }

    String getConfPath() {
        return getWorkingDir() + getResourceName()
    }

    void report(String msg) {
        println(getModuleName().padRight(16) + ": " + Thread.currentThread().getName() + ": " + msg)
    }

}
