package es.sfernandez.sqg.beans

import es.sfernandez.sqg.beans.question.problems.Problem

data class Questionary(
    var problems : Array<Problem>
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Questionary

        return problems.contentEquals(other.problems)
    }

    override fun hashCode(): Int {
        return problems.contentHashCode()
    }

    class Builder {

        private val problems : MutableList<Problem> = mutableListOf();

        fun add(problem : Problem) : Builder {
            problems.add(problem);
            return this;
        }

        fun build() : Questionary {
            return Questionary(problems.toTypedArray());
        }

    }

}