package es.sfernandez.sqg.model.answers.replies

import es.sfernandez.sqg.model.answers.choices.Choice
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class MultipleReplyTest {

    //---- Constants and Definitions ----
    private class FooChoice : Choice

    //---- Attributes ----
    private lateinit var reply : MultipleReply

    //---- Fixtures ----
    private val someChoices : Array<Choice> = generateSomeChoices()

    private fun generateSomeChoices() : Array<Choice> {
        return generateSequence { FooChoice() }.take(5).toList().toTypedArray()
    }

    //---- Tests ----
    @Test
    fun get_returnsChoiceGivenInConstructionTest() {
        val choices = someChoices

        reply = MultipleReply(choices)

        Assertions.assertThat(reply.get()).contains(*choices)
    }

}