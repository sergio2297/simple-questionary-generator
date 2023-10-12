package es.sfernandez.sqg.utilities.fixtures.model.correcting.replies

import es.sfernandez.sqg.model.correcting.replies.Reply
import org.mockito.Mockito

interface UsesReplyFixtures {

    fun aReply(): Reply<*> {
        return Mockito.mock(Reply::class.java)
    }

}