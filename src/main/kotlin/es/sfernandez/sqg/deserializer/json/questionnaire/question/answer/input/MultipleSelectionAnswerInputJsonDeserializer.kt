package es.sfernandez.sqg.deserializer.json.questionnaire.question.answer.input

import es.sfernandez.sqg.beans.question.answers.input.MultipleSelectionAnswerInput
import es.sfernandez.sqg.deserializer.json.questionnaire.question.ChoiceJsonDeserializer

internal class MultipleSelectionAnswerInputJsonDeserializer : SelectionAnswerInputJsonDeserializer<MultipleSelectionAnswerInput> {

    //---- Constructor ----
    constructor() : super(MultipleSelectionAnswerInput::class.java)

    constructor(choiceDeserializer: ChoiceJsonDeserializer) : super(MultipleSelectionAnswerInput::class.java, choiceDeserializer)

    //---- Methods ----
    override fun instantiateInput(): MultipleSelectionAnswerInput {
        return MultipleSelectionAnswerInput()
    }

}