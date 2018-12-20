package io.infinite.supplies.conf


import java.security.AccessController
import java.security.PrivilegedAction

class ResourceLookupThread extends ResourceLookupSystem {

    ResourceLookupThread(String moduleName, String resourceName, Boolean proceedSearch) {
        super(moduleName, resourceName, proceedSearch)
    }

    File getResourceAsFile() {
        report("Searching for ${getResourceName()} config in application resource files using Thread classloader.")
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
        report("(ResourceLookup name: ${getResourceName()})")
        ClassLoader classLoader = getClassLoader()
        if (classLoader != null) {
            return classLoader.getResource(getResourceName())
        } else {
            return super.getResource()
        }
    }

    ClassLoader getClassLoader() {
        report("Using thread classloader")
        return Thread.currentThread().getContextClassLoader()
    }


}
