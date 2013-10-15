package org.springframework.data.aerospike.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.data.aerospike.service.VOMarker;


/**
 * Annotation to be applied on the object to be saved in Aerospike. Such objects (aka VOs) should implement
 * {@link VOMarker}. Analogous to table of RDBMS.
 * 
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Set {

    /**
     * Name of the schema to which this object (table) belongs.
     * 
     * @return
     */
    public String namespace();

    /**
     * Name of the VO. Or table name.
     * 
     * @return
     */
    public String name();
}
