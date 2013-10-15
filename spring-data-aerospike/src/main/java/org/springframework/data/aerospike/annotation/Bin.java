package org.springframework.data.aerospike.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to be applied on fields of the object to be saved in Aerospike. Analogous to column of RDBMS.
 * 
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Bin {

    /**
     * Column name.
     * 
     * @return
     */
    public String name();

}
