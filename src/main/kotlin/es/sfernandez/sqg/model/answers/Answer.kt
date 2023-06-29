package es.sfernandez.sqg.model.answers

import es.sfernandez.sqg.model.answers.replies.Reply


abstract class Answer<in REPLY : Reply<*>>(
    val type : AnswerTypes
) {

    abstract fun isRight(reply : REPLY) : Boolean

}