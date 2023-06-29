package es.sfernandez.sqg.model.answers.replies

import es.sfernandez.sqg.model.answers.Answer

/**
 * Represents a reply given to an [Answer].
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