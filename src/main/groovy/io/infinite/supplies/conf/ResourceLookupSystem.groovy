package io.infinite.supplies.conf


import java.security.AccessController
import java.security.PrivilegedAction

class ResourceLookupSystem extends ResourceLookupWorkingDir {

    ResourceLookupSystem(String moduleName, String resourceName, Boolean proceedSearch) {
        super(moduleName, resourceName, proceedSearch)
    }

    String getResourceAsString() {
        report("Searching for Bobbin config in application resource files using System classloader.")
        URL url = AccessController.doPrivileged(new PrivilegedAction<URL>() {
            URL run() {
                return ClassLoader.getSystemClassLoader().getResource(getResourceName())
            }
        })
        if (url != null) {
            report("Found: " + url.toExternalForm())
            return new Scanner(ClassLoader.getSystemClassLoader().getResourceAsStream(getResourceName())).useDelimiter("\\A").next()
        }
        report("Not found.")
        if (proceedSearch) {
            return super.getResourceAsString()
        } else {
            return null
        }

    }

}