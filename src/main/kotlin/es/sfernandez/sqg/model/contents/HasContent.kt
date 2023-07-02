package es.sfernandez.sqg.model.contents

/**
 * A class that implements HasContent implies it has a [ContentManager]
 */
interface HasContent {

    val contentManager: ContentManager

}