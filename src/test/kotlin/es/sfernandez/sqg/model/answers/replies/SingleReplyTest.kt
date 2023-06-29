package es.sfernandez.sqg.model.answers.replies

import es.sfernandez.sqg.model.answers.choices.Choice
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SingleReplyTest {

    //---- Constants and Definitions ----
    private class FooChoice : Choice

    //---- Attributes ----
    private lateinit var reply : SingleReply

    //---- Fixtures ----
    private val aChoice = FooChoice()

    //---- Tests ----
    @Test
    fun get_returnsChoiceGivenInConstructionTest() {
        val choice : Choice = aChoice

        reply = SingleReply(choice)

        assertThat(reply.get()).isSameAs(choice)
    }
}