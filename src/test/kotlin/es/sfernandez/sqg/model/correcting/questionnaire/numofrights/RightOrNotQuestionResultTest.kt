package es.sfernandez.sqg.model.correcting.questionnaire.numofrights

import es.sfernandez.sqg.beans.question.Question
import es.sfernandez.sqg.model.correcting.replies.Reply
import org.assertj.core.api.Assertions.assertThat
import org.mockito.Mockito
import kotlin.test.Test

class RightOrNotQuestionResultTest {

    //---- Methods ----
    private fun createQuestion(): Question {
        return Mockito.mock(Question::class.java)
    }

    private fun createReply(): Reply<*> {
        return Mockito.mock(Reply::class.java)
    }

    //---- Tests ----
    @Test
    fun afterConstruct_questionIsAssignedCorrectlyTest() {
        val question = createQuestion()

        val result = RightOrNotQuestionResult(question, createReply(), true)

        assertThat(result.question).isSameAs(question)
    }

    @Test
    fun afterConstruct_replyIsAssignedCorrectlyTest() {
        val reply = createReply()

        val result = RightOrNotQuestionResult(createQuestion(), reply, true)

        assertThat(result.reply).isSameAs(reply)
    }

    @Test
    fun afterConstruct_isRightIsAssignedCorrectlyTest() {
        val isRight = true

        val result = RightOrNotQuestionResult(createQuestion(), createReply(), isRight)

        assertThat(result.isRight).isSameAs(isRight)
    }

}