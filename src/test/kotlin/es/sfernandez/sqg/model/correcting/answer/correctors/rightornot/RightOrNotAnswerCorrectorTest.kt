package es.sfernandez.sqg.model.correcting.answer.correctors.rightornot

import es.sfernandez.sqg.beans.question.answers.correction.AnswerCorrection
import es.sfernandez.sqg.beans.question.answers.correction.rightornot.RightOrNotCorrection
import es.sfernandez.sqg.model.correcting.answer.replies.Reply
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.Mockito
import org.mockito.kotlin.never
import java.util.stream.Stream
import kotlin.test.Test
import kotlin.test.fail

class RightOrNotAnswerCorrectorTest {

    //---- Constants and Definitions ----
    private class FooRightOrNotAnswerCorrector : RightOrNotAnswerCorrector() {

        override fun accept(reply: Reply<*>): Boolean {
            fail("This method shouldn't be tested")
        }

        override fun safeCorrect(correction: AnswerCorrection, reply: Reply<*>): Boolean {
            fail("This method shouldn't be tested")
        }
    }

    //---- Attributes ----
    private val corrector: RightOrNotAnswerCorrector = FooRightOrNotAnswerCorrector()

    //---- Methods ----
    companion object {
        private fun rightOrNotCorrection(): AnswerCorrection {
            return Mockito.mock(RightOrNotCorrection::class.java)
        }

        private fun notRightOrNotCorrection(): AnswerCorrection {
            return Mockito.mock(AnswerCorrection::class.java)
        }

        @JvmStatic
        fun answerCorrectionStream(): Stream<AnswerCorrection> {
            return Stream.of(rightOrNotCorrection(), notRightOrNotCorrection())
        }
    }

    //---- Tests ----
    @Test
    fun acceptCorrection_returnsTrueIfCorrection_isInstanceOfRightOrNotCorrectionTest() {
        val correction = rightOrNotCorrection()

        val accepted = corrector.accept(correction)

        assertThat(accepted).isTrue()
    }

    @Test
    fun acceptCorrection_returnsFalseIfCorrection_isNotInstanceOfRightOrNotCorrectionTest() {
        val correction = notRightOrNotCorrection()

        val accepted = corrector.accept(correction)

        assertThat(accepted).isFalse()
    }

    @ParameterizedTest
    @MethodSource("answerCorrectionStream")
    fun acceptCorrection_ignoresCorrectionTypeTest(correction: AnswerCorrection) {
        corrector.accept(correction)

        Mockito.verify(correction, never()).type
    }

}