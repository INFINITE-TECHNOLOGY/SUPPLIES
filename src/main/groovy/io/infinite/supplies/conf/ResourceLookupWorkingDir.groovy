package io.infinite.supplies.conf

class ResourceLookupWorkingDir {

    String moduleName
    String resourceName
    Boolean proceedSearch

    ResourceLookupWorkingDir(String moduleName, String resourceName, Boolean proceedSearch) {
        this.moduleName = moduleName
        this.resourceName = resourceName
        this.proceedSearch = proceedSearch
    }

    File getResourceAsFile() {
        report("Searching for ${getResourceName()} config in: " + getConfPath() + " (full path: ${new File(getConfPath()).getCanonicalPath()})")
        File file = new File(getConfPath())
        if (file.exists()) {
            report("Found: " + file.getCanonicalPath())
            return file
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
        println(getModuleName().padRight(16) + ": " + msg)
    }

}
