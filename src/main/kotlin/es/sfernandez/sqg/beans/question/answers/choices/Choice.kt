package es.sfernandez.sqg.beans.question.answers.choices

import es.sfernandez.sqg.beans.contents.Content

interface Choice {

    val id : String
    val content : Content

    companion object {
        fun create(id : String, content : Content) : Choice {
            return object : Choice {
                override val id: String
                    get() = id
                override val content: Content
                    get() = content

            }
        }
    }

}