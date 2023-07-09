package es.sfernandez.sqg.model.contents

/**
 * IsResource implies that the object has a path that points to some resource: media, file...
 */
interface IsResource {

    /** path of the resource (usually it will be absolute, but depends on the implementation) */
    val path : String;

}