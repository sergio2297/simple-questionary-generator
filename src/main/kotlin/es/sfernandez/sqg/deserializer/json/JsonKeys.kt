package es.sfernandez.sqg.deserializer.json

/**
 * Object which stores all json keys for values of every entity that can be deserialized by [JsonDeserializer]
 *
 * This can be used to protected ourselves from further refactor
 *
 */
object JsonKeys {

    object Questionnaire {
        const val TITLE = "title"
        const val PORTRAIT = "portrait"
        const val QUESTIONS = "questions"
    }

    object Question {
        const val TITLE = "title"
        const val PROBLEM = "problem"
        const val ANSWER = "answer"
        const val EXPLANATION = "explanation"
    }

    object Problem {
        const val CONTENTS = "contents"
    }

    object Explanation {
        const val CONTENTS = "contents"
    }

    object Answer {

        const val INPUT = "input"
        const val CORRECTION = "correction"

        object Input {

            const val TYPE = "type"

            object Selection {
                const val POSSIBLE_CHOICES = "possibleChoices"
            }

        }

        object Correction {

            const val TYPE = "type"

            object RightOrNot {

                const val IDS = "ids"
                const val VALUES = "values"
                const val REGEX = "regex"

            }

        }

    }

    object Choice {
        const val ID = "id"
        const val CONTENT = "content"
    }

    object Contents {

        object Text {
            const val VALUE = "value"
            const val MARKUP = "markup"
        }

        object Sound {
            const val PATH = "path"
            const val AUTOPLAY = "autoplay"
        }

        object Image {
            const val PATH = "path"
            const val CLICK_TO_SEE = "clickToSee"
        }

        object Video {
            const val PATH = "path"
            const val AUTOPLAY = "autoplay"
        }

    }

}