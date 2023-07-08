package es.sfernandez.sqg.beans.contents

/**
 * A class that implements HasContent implies it has a [GroupOfContents]
 */
interface HasContents {

    val groupOfContents: GroupOfContents

}