package es.sfernandez.sqg.deserializer.json

import es.sfernandez.sqg.beans.Questionnaire
import es.sfernandez.sqg.beans.contents.*
import es.sfernandez.sqg.beans.question.Question
import es.sfernandez.sqg.beans.question.answers.Answer
import es.sfernandez.sqg.beans.question.answers.Choice
import es.sfernandez.sqg.beans.question.answers.correction.AnswerCorrection
import es.sfernandez.sqg.beans.question.answers.input.AnswerInput
import es.sfernandez.sqg.beans.question.explanations.Explanation
import es.sfernandez.sqg.beans.question.problems.Problem
import es.sfernandez.sqg.deserializer.Deserializer
import es.sfernandez.sqg.deserializer.DeserializerFactory
import es.sfernandez.sqg.deserializer.json.questionnaire.QuestionnaireJsonDeserializer
import es.sfernandez.sqg.deserializer.json.questionnaire.contents.*
import es.sfernandez.sqg.deserializer.json.questionnaire.question.ChoiceJsonDeserializer
import es.sfernandez.sqg.deserializer.json.questionnaire.question.ExplanationJsonDeserializer
import es.sfernandez.sqg.deserializer.json.questionnaire.question.ProblemJsonDeserializer
import es.sfernandez.sqg.deserializer.json.questionnaire.question.QuestionJsonDeserializer
import es.sfernandez.sqg.deserializer.json.questionnaire.question.answer.AnswerJsonDeserializer
import es.sfernandez.sqg.deserializer.json.questionnaire.question.answer.correction.AnswerCorrectionJsonDeserializer
import es.sfernandez.sqg.deserializer.json.questionnaire.question.answer.input.AnswerInputJsonDeserializer

class JsonDeserializerFactory : DeserializerFactory {

    override fun createGroupOfContentsDeserializer(): Deserializer<GroupOfContents> {
        return GroupOfContentsJsonDeserializer()
    }

    override fun createTextDeserializer(): Deserializer<Text> {
        return TextJsonDeserializer()
    }

    override fun createSoundDeserializer(): Deserializer<Sound> {
        return SoundJsonDeserializer()
    }

    override fun createImageDeserializer(): Deserializer<Image> {
        return ImageJsonDeserializer()
    }

    override fun createVideoDeserializer(): Deserializer<Video> {
        return VideoJsonDeserializer()
    }

    override fun createProblemDeserializer() : JsonDeserializer<Problem> {
        return ProblemJsonDeserializer()
    }

    override fun createAnswerDeserializer(): Deserializer<Answer> {
        return AnswerJsonDeserializer()
    }

    override fun createAnswerCorrectionDeserializer(): Deserializer<AnswerCorrection> {
        return AnswerCorrectionJsonDeserializer()
    }

    override fun createAnswerInputDeserializer(): Deserializer<AnswerInput> {
        return AnswerInputJsonDeserializer()
    }

    override fun createChoiceDeserializer(): Deserializer<Choice> {
        return ChoiceJsonDeserializer()
    }

    override fun createExplanationDeserializer(): Deserializer<Explanation> {
        return ExplanationJsonDeserializer()
    }

    override fun createQuestionDeserializer(): Deserializer<Question> {
        return QuestionJsonDeserializer()
    }

    override fun createQuestionnaireDeserializer(): Deserializer<Questionnaire> {
        return QuestionnaireJsonDeserializer()
    }

}