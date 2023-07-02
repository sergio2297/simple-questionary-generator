package es.sfernandez.sqg.model.contents

import es.sfernandez.sqg.model.question.answers.AnswerFixtures
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ContentManagerTest {

    //---- Attributes ----
    private lateinit var manager : ContentManager

    //---- Fixtures ----
    private val someContents = generateSequence { AnswerFixtures.FooContent() }.take(5).toList()

    //---- Configuration ----
    @BeforeEach
    fun setup() {
        manager = ContentManager()
    }

    //---- Methods ----
    private fun addContentsToManager(contents: List<Content>) {
        manager.add(*contents.toTypedArray())
    }

    private fun checkIfContentIn(content: Content, contentsIt: Iterator<Content>) : Boolean {
        return contentsIt.asSequence().find { c -> c == content } != null
    }

    //---- Tests ----
    @Test
    fun newContentManager_isEmptyTest() {
        val manager = ContentManager()

        assertThat(manager.content().hasNext()).isFalse()
    }

    @Test
    fun addContent_worksTest() {
        addContentsToManager(someContents)

        someContents.forEach { content -> assertThat(checkIfContentIn(content, manager.content())).isTrue() }
    }

    @Test
    fun removeContent_worksTest() {
        val removedContent = someContents[0]
        addContentsToManager(someContents)

        manager.remove(removedContent)

        assertThat(checkIfContentIn(removedContent, manager.content())).isFalse()
    }

}