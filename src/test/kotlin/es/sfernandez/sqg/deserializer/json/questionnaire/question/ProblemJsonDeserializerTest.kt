package es.sfernandez.sqg.deserializer.json.questionnaire.question

import es.sfernandez.sqg.beans.contents.GroupOfContents
import es.sfernandez.sqg.beans.contents.HasContents
import es.sfernandez.sqg.deserializer.json.JsonDeserializer
import es.sfernandez.sqg.deserializer.json.JsonKeys
import es.sfernandez.sqg.deserializer.json.questionnaire.contents.GroupOfContentsJsonDeserializer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ProblemJsonDeserializerTest : NeedsToDeserializeGroupOfContentsTest {

    //---- Attributes ----
    override var deserializer: JsonDeserializer<out HasContents> = createNormalDeserializer()
    override lateinit var groupOfContentsDeserializer: GroupOfContentsJsonDeserializer
    override lateinit var contents: GroupOfContents
    override val contentsKey: String
        get() = JsonKeys.Problem.CONTENTS

    //---- Configuration ----
    @BeforeEach
    fun setup() {
        deserializer = createNormalDeserializer()
    }

    //---- Methods ----
    private fun createNormalDeserializer(): ProblemJsonDeserializer {
        return ProblemJsonDeserializer()
    }

    override fun createMockedDeserializer(groupOfContentsDeserializer: GroupOfContentsJsonDeserializer): JsonDeserializer<out HasContents> {
        return ProblemJsonDeserializer(groupOfContentsDeserializer)
    }

    //---- Tests ----
    @Test
    fun problemJsonDeserializer_isInstanceOf_JsonDeserializerTest() {
        assertThat(ProblemJsonDeserializer()).isInstanceOf(JsonDeserializer::class.java)
    }

}