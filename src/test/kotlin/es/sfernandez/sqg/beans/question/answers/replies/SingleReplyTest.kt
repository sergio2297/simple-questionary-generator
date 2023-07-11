package es.sfernandez.sqg.beans.question.answers.replies

import es.sfernandez.sqg.beans.question.answers.choices.Choice
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SingleReplyTest {

    //---- Attributes ----
    private lateinit var reply : SingleReply

    //---- Fixtures ----
    private val aChoice = Choice()

    //---- Tests ----
    @Test
    fun get_returnsChoiceGivenInConstructionTest() {
        val choice : Choice = aChoice

        reply = SingleReply(choice)

        assertThat(reply.get()).isSameAs(choice)
    }
}