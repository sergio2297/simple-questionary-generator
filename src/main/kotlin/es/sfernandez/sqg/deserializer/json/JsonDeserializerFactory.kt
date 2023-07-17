package es.sfernandez.sqg.deserializer.json

import es.sfernandez.sqg.beans.Questionary
import es.sfernandez.sqg.deserializer.Deserializer
import es.sfernandez.sqg.deserializer.DeserializerFactory
import es.sfernandez.sqg.deserializer.json.questionary.question.ExplanationJsonDeserializer
import es.sfernandez.sqg.deserializer.json.questionary.question.ProblemJsonDeserializer
import es.sfernandez.sqg.deserializer.json.questionary.contents.*
import es.sfernandez.sqg.beans.contents.*
import es.sfernandez.sqg.beans.question.Question
import es.sfernandez.sqg.beans.question.answers.Answer
import es.sfernandez.sqg.beans.question.answers.choices.Choice
import es.sfernandez.sqg.beans.question.explanations.Explanation
import es.sfernandez.sqg.beans.question.problems.Problem
import es.sfernandez.sqg.deserializer.json.questionary.QuestionaryJsonDeserializer
import es.sfernandez.sqg.deserializer.json.questionary.question.ChoiceJsonDeserializer
import es.sfernandez.sqg.deserializer.json.questionary.question.QuestionJsonDeserializer
import es.sfernandez.sqg.deserializer.json.questionary.question.answer.AnswerJsonDeserializer

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

    override fun createChoiceDeserializer(): Deserializer<Choice> {
        return ChoiceJsonDeserializer()
    }

    override fun createExplanationDeserializer(): Deserializer<Explanation> {
        return ExplanationJsonDeserializer()
    }

    override fun createQuestionDeserializer(): Deserializer<Question> {
        return QuestionJsonDeserializer()
    }

    override fun createQuestionaryDeserializer(): Deserializer<Questionary> {
        return QuestionaryJsonDeserializer()
    }

}