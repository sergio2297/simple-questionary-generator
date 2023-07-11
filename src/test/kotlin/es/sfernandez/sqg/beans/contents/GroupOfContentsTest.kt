package es.sfernandez.sqg.beans.contents

import es.sfernandez.sqg.beans.question.answers.AnswerFixtures
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class GroupOfContentsTest {

    //---- Attributes ----
    private lateinit var manager : GroupOfContents

    //---- Fixtures ----
    private val someContents = generateSequence { AnswerFixtures.FooContent() }.take(5).toList()

    //---- Configuration ----
    @BeforeEach
    fun setup() {
        manager = GroupOfContents()
    }

    //---- Methods ----
    private fun addContentsToManager(contents: List<Content>) {
        manager.add(*contents.toTypedArray())
    }

    //---- Tests ----
    @Test
    fun newContentManager_isEmptyTest() {
        val manager = GroupOfContents()

        assertThat(manager.contents()).isEmpty()
    }

    @Test
    fun newContentManager_hasZeroElementsTest() {
        val manager = GroupOfContents()

        assertThat(manager.size()).isZero()
    }

    @Test
    fun addContent_worksTest() {
        addContentsToManager(someContents)

        assertThat(manager.contents()).containsExactlyInAnyOrder(*someContents.toTypedArray())
    }

    @Test
    fun addContent_increaseSizeTest() {
        val numElementsAdded = someContents.size

        addContentsToManager(someContents)

        assertThat(manager.size()).isEqualTo(numElementsAdded)
    }

    @Test
    fun addedContent_canBeObtainedBy_getMethodTest() {
        val firstElement = someContents[0]
        addContentsToManager(someContents)

        val content = manager.get(0)

        assertThat(content).isEqualTo(firstElement)
    }

    @Test
    fun getContent_withOutOfBoundsIndex_throwsExceptionTest() {
        val numElementsAdded = someContents.size
        addContentsToManager(someContents)

        assertThrows<IndexOutOfBoundsException> { manager.get(numElementsAdded + 1) }
    }

    @Test
    fun removeContent_worksTest() {
        val removedContent = someContents[0]
        addContentsToManager(someContents)

        manager.remove(removedContent)

        assertThat(manager.contents()).doesNotContain(removedContent)
    }

    @Test
    fun removeContent_decreaseSizeTest() {
        addContentsToManager(someContents)
        val initialNumElements = manager.size()

        manager.remove(someContents[0])

        assertThat(manager.size()).isEqualTo(initialNumElements - 1)
    }

}