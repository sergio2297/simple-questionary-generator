package es.sfernandez.sqg.model.contents

/**
 * A class that implements HasContent implies it has a [GroupOfContents]
 */
interface HasContents {

    val groupOfContents: GroupOfContents

}