package es.sfernandez.sqg.deserializer.json.questionnaire.question.answer.input

import es.sfernandez.sqg.beans.question.answers.input.SingleSelectionAnswerInput
import es.sfernandez.sqg.deserializer.json.questionnaire.question.ChoiceJsonDeserializer

internal class SingleSelectionAnswerInputJsonDeserializer : SelectionAnswerInputJsonDeserializer<SingleSelectionAnswerInput> {

    //---- Constructor ----
    constructor() : super(SingleSelectionAnswerInput::class.java)

    constructor(choiceDeserializer: ChoiceJsonDeserializer) : super(SingleSelectionAnswerInput::class.java, choiceDeserializer)

    //---- Methods ----
    override fun instantiateInput(): SingleSelectionAnswerInput {
        return SingleSelectionAnswerInput()
    }

}