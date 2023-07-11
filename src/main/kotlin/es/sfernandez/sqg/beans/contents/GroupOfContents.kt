package es.sfernandez.sqg.beans.contents

/**
 * A GroupOfContents stores a set of [Content] in an ordered way
 *
 * @constructor Create empty Group of contents
 */
class GroupOfContents {

    //---- Attributes ----
    private val contents: MutableList<Content> = mutableListOf()

    //---- Methods ----
    /**
     * Add the contents to the group
     *
     * @param contents Contents to add
     */
    fun add(vararg contents: Content) {
        contents.forEach { content -> this.contents.add(content) }
    }

    /**
     * Remove the contents from the group
     *
     * @param content Content to remove
     */
    fun remove(content: Content) {
        contents.remove(content)
    }

    /**
     * @return the actual amount of Contents
     */
    fun size() : Int {
        return contents.size
    }

    /**
     * @param index Position of the Content to get
     * @return the content at the given position
     * @throws IndexOutOfBoundsException if the index is out of bounds
     */
    fun get(index : Int) : Content {
        return contents[index]
    }

    /**
     * @return an unmodifiable list of contents
     */
    fun contents() : List<Content> {
        return contents.toList()
    }
}