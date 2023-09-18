package es.sfernandez.sqg.utilities.mocking

import es.sfernandez.sqg.model.correcting.replies.Reply
import org.mockito.Mockito

internal interface MocksReply {

    fun mockReply(): Reply<*> {
        return Mockito.mock(Reply::class.java)
    }

}