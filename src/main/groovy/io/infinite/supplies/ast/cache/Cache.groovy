package io.infinite.supplies.ast.cache


import org.codehaus.groovy.transform.GroovyASTTransformationClass

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

/**
 * Caches the initialization of annotated field. <br>
 *     Cache is on class level (is static). <br>
 * Field should be final. <br>
 * Field should not be static. <br>
 *     This is an eager cache <br>
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@GroovyASTTransformationClass("io.infinite.supplies.ast.cache.CacheTransformation")
@interface Cache {

}