package es.sfernandez.model

data class Questionary(
    var questions : Array<Question>
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Questionary

        return questions.contentEquals(other.questions)
    }

    override fun hashCode(): Int {
        return questions.contentHashCode()
    }

    class Builder {

        private val questions : MutableList<Question> = mutableListOf();

        fun add(question : Question) : Builder {
            questions.add(question);
            return this;
        }

        fun build() : Questionary {
            return Questionary(questions.toTypedArray());
        }

    }

}