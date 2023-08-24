package es.sfernandez.sqg.model.correcting.answer.correctors.rightornot

import es.sfernandez.sqg.beans.question.answers.correction.AnswerCorrection
import es.sfernandez.sqg.beans.question.answers.correction.rightornot.RightOrNotCorrection
import es.sfernandez.sqg.model.correcting.answer.correctors.AnswerCorrectorTest
import es.sfernandez.sqg.model.correcting.replies.Reply
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
        AnswerCorrectorTest.checkIfCorrectorAcceptCorrection(corrector, RightOrNotCorrection::class.java)
    }

    @ParameterizedTest
    @MethodSource("answerCorrectionStream")
    fun acceptCorrection_ignoresCorrectionTypeTest(correction: AnswerCorrection) {
        corrector.accept(correction)

        Mockito.verify(correction, never()).type
    }

}