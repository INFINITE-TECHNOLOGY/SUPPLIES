package io.infinite.supplies.conf


import java.security.AccessController
import java.security.PrivilegedAction

class ResourceLookupSystem extends ResourceLookupWorkingDir {

    ResourceLookupSystem(String moduleName, String resourceName, Boolean proceedSearch) {
        super(moduleName, resourceName, proceedSearch)
    }

    File getResourceAsFile() {
        report("Searching for Bobbin config in application resource files using System classloader.")
        URL url = AccessController.doPrivileged(new PrivilegedAction<URL>() {
            URL run() {
                return getResource()
            }
        })
        if (url != null) {
            report("Found: " + new File(url.toURI()).getCanonicalPath())
            return new File(url.toURI())
        } else {
            report("Not found.")
            if (proceedSearch) {
                return super.getResourceAsFile()
            } else {
                return null
            }
        }
    }

    URL getResource() {
        return ClassLoader.getSystemResource(getResourceName())
    }

}