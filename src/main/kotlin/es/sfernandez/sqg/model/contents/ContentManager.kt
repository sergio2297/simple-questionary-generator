package es.sfernandez.sqg.model.contents

class ContentManager {

    //---- Attributes ----
    private val contents: MutableList<Content> = mutableListOf()

    //---- Methods ----
    fun add(vararg contents: Content) {
        contents.forEach { content -> this.contents.add(content) }
    }

    fun remove(content: Content) {
        contents.remove(content)
    }

    fun content() : Iterator<Content> {
        return contents.iterator()
    }
}