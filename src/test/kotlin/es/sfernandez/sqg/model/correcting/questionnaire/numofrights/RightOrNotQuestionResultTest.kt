package es.sfernandez.sqg.model.correcting.questionnaire.numofrights

import es.sfernandez.sqg.utilities.mocking.MocksQuestion
import es.sfernandez.sqg.utilities.mocking.MocksReply
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class RightOrNotQuestionResultTest : MocksQuestion, MocksReply {

    //---- Tests ----
    @Test
    fun afterConstruct_questionIsAssignedCorrectlyTest() {
        val question = aQuestion()

        val result = RightOrNotQuestionResult(question, mockReply(), true)

        assertThat(result.question).isSameAs(question)
    }

    @Test
    fun afterConstruct_replyIsAssignedCorrectlyTest() {
        val reply = mockReply()

        val result = RightOrNotQuestionResult(aQuestion(), reply, true)

        assertThat(result.reply).isSameAs(reply)
    }

    @Test
    fun afterConstruct_isRightIsAssignedCorrectlyTest() {
        val isRight = true

        val result = RightOrNotQuestionResult(aQuestion(), mockReply(), isRight)

        assertThat(result.isRight).isSameAs(isRight)
    }

}