package es.sfernandez.sqg.model.correcting.replies

import es.sfernandez.sqg.beans.question.Question

/**
 * Represents a reply given to a [Question].
 *
 * It only provides a getter to the value stored by the reply. For example, a String typed by someone.
 *
 * @param T Type of value stored by the reply
 * @author sfernandez
 */
interface Reply<out T> {

    /**
     * @return the stored value by the reply
     */
    fun get() : T

}