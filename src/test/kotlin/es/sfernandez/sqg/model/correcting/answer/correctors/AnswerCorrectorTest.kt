package es.sfernandez.sqg.model.correcting.answer.correctors

import es.sfernandez.sqg.BasicFixtures
import es.sfernandez.sqg.beans.question.answers.correction.AnswerCorrection
import es.sfernandez.sqg.model.correcting.answer.replies.Reply
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.fail
import org.mockito.Mockito

class AnswerCorrectorTest {

    //---- Constants and Definitions ----
    private class FooAnswerCorrector(
        private val acceptAllCorrections: Boolean,
        private val acceptAllReplies: Boolean,
        private val safeCorrectResult: BasicFixtures.Foo = BasicFixtures.Foo(),
    ) : AnswerCorrector<BasicFixtures.Foo>() {

        override fun accept(correction: AnswerCorrection): Boolean {
            return acceptAllCorrections
        }

        override fun accept(reply: Reply<*>): Boolean {
            return acceptAllReplies
        }

        override fun safeCorrect(correction: AnswerCorrection, reply: Reply<*>): BasicFixtures.Foo {
            return safeCorrectResult
        }
    }

    //---- Attributes ----
    private lateinit var corrector: AnswerCorrector<BasicFixtures.Foo>

    //---- Fixtures ----
    private val aCorrection = Mockito.mock(AnswerCorrection::class.java)
    private val aReply = Mockito.mock(Reply::class.java)

    //---- Methods ----
    private fun correctorThatAcceptAll(): AnswerCorrector<BasicFixtures.Foo> {
        return FooAnswerCorrector(acceptAllCorrections = true, acceptAllReplies = true)
    }

    private fun correctorThatDoNotAcceptCorrections(): AnswerCorrector<BasicFixtures.Foo> {
        return FooAnswerCorrector(acceptAllCorrections = false, acceptAllReplies = true)
    }

    private fun correctorThatDoNotAcceptReplies(): AnswerCorrector<BasicFixtures.Foo> {
        return FooAnswerCorrector(acceptAllCorrections = true, acceptAllReplies = false)
    }

    //---- Tests ----
    @Test
    fun correct_withNotAcceptedCorrection_throwsExceptionTest() {
        corrector = correctorThatDoNotAcceptCorrections()

        assertThrows<AnswerCorrectorException> { corrector.correct(aCorrection, aReply) }
    }

    @Test
    fun exception_thrownIfNotAcceptedCorrection_containsClassNamesTest() {
        corrector = correctorThatDoNotAcceptCorrections()

        try {
            corrector.correct(aCorrection, aReply)
            fail("No exception was thrown.")
        } catch (ex: AnswerCorrectorException) {
            assertThat(ex.message)
                .contains(corrector.javaClass.canonicalName)
                .contains(aCorrection.javaClass.canonicalName)
        }
    }

    @Test
    fun correct_withNotAcceptedReply_throwsExceptionTest() {
        corrector = correctorThatDoNotAcceptReplies()

        assertThrows<AnswerCorrectorException> { corrector.correct(aCorrection, aReply) }
    }

    @Test
    fun exception_thrownIfNotAcceptedReply_containsClassNamesTest() {
        corrector = correctorThatDoNotAcceptReplies()

        try {
            corrector.correct(aCorrection, aReply)
            fail("No exception was thrown.")
        } catch (ex: AnswerCorrectorException) {
            assertThat(ex.message)
                .contains(corrector.javaClass.canonicalName)
                .contains(aReply.javaClass.canonicalName)
        }
    }

    @Test
    fun safeCorrect_isExecutedIff_correctArgumentsAreAcceptedTest() {
        corrector = correctorThatAcceptAll()

        val result = corrector.correct(aCorrection, aReply)

        assertThat(result).isInstanceOf(BasicFixtures.Foo::class.java)
    }

}