package es.sfernandez.sqg.beans.question.answers

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class UnknownAnswerTest {

    //---- Attributes ----
    private lateinit var answer: UnknownAnswer

    //---- Tests ----
    @Test
    fun unknownAnswersAre_instanceOfAnswerTest() {
        answer = UnknownAnswer()

        Assertions.assertThat(answer).isInstanceOf(Answer::class.java)
    }

    @Test
    fun unknownAnswersAre_UnknownAnswerTypeTest() {
        answer = UnknownAnswer()

        Assertions.assertThat(answer.type).isEqualTo(AnswerTypes.UNKNOWN)
    }
    
}