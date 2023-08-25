package es.sfernandez.sqg.deserializer.json.questionnaire.question

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import es.sfernandez.sqg.beans.question.Question
import es.sfernandez.sqg.beans.question.answers.Answer
import es.sfernandez.sqg.beans.question.explanations.Explanation
import es.sfernandez.sqg.beans.question.problems.Problem
import es.sfernandez.sqg.deserializer.json.JsonDeserializer
import es.sfernandez.sqg.deserializer.json.JsonKeys
import es.sfernandez.sqg.deserializer.json.questionnaire.question.answer.AnswerJsonDeserializer

class QuestionJsonDeserializer : JsonDeserializer<Question> {

    //---- Attributes ----
    private val problemDeserializer: ProblemJsonDeserializer
    private val answerDeserializer: AnswerJsonDeserializer
    private val explanationDeserializer: ExplanationJsonDeserializer

    //---- Constructor ----
    internal constructor(problemDeserializer: ProblemJsonDeserializer,
                         answerDeserializer: AnswerJsonDeserializer,
                         explanationDeserializer: ExplanationJsonDeserializer) : super(Question::class.java) {
        this.problemDeserializer = problemDeserializer
        this.answerDeserializer = answerDeserializer
        this.explanationDeserializer = explanationDeserializer
    }

    constructor() : super(Question::class.java) {
        this.problemDeserializer = ProblemJsonDeserializer()
        this.answerDeserializer = AnswerJsonDeserializer()
        this.explanationDeserializer = ExplanationJsonDeserializer()
    }

    //---- Methods ----
    override fun createDeserializer(): StdDeserializer<Question> {
        return CustomDeserializer()
    }

    private inner class CustomDeserializer : StdDeserializer<Question>(mappedClass) {

        override fun deserialize(parser: JsonParser?, ctxt: DeserializationContext?): Question {
            val node = extractJsonNode(parser)

            val question = Question()
            question.title = extractText(node, JsonKeys.Question.TITLE)
            question.problem = extractObject(node, JsonKeys.Question.PROBLEM, problemDeserializer, Problem())
                .also { dumpLogsFrom(problemDeserializer) }
            question.answer = extractObject(node, JsonKeys.Question.ANSWER, answerDeserializer, Answer())
                .also { dumpLogsFrom(answerDeserializer) }
            question.explanation = extractObject(node, JsonKeys.Question.EXPLANATION, explanationDeserializer, Explanation())
                .also { dumpLogsFrom(explanationDeserializer) }

            return question
        }

    }

}