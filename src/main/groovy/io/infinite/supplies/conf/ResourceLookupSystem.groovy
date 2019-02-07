package io.infinite.supplies.conf

import groovy.transform.CompileStatic

import java.security.AccessController
import java.security.PrivilegedAction

@CompileStatic
class ResourceLookupSystem extends ResourceLookupAbstract {

    ResourceLookupSystem(String moduleName, String resourceName, Boolean proceedSearch) {
        super(moduleName, resourceName, proceedSearch)
    }

    ResourceLookupSystem(String moduleName, String resourceName) {
        super(moduleName, resourceName)
    }

    String getResourceAsString() {
        report("Searching for Bobbin config in application resource files using System classloader.")
        URL url = AccessController.doPrivileged(new PrivilegedAction<URL>() {
            URL run() {
                return ClassLoader.getSystemClassLoader().getResource(resourceName)
            }
        })
        if (url != null) {
            report("Found: " + url.toExternalForm())
            return new Scanner(ClassLoader.getSystemClassLoader().getResourceAsStream(resourceName)).useDelimiter("\\A").next()
        }
        report("Not found.")
        return null
    }

}