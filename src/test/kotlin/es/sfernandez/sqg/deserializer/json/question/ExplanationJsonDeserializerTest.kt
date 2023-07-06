package es.sfernandez.sqg.deserializer.json.question

import es.sfernandez.sqg.deserializer.json.JsonDeserializer
import es.sfernandez.sqg.deserializer.json.JsonKeys
import es.sfernandez.sqg.deserializer.json.question.contents.GroupOfContentsJsonDeserializer
import es.sfernandez.sqg.model.contents.GroupOfContents
import es.sfernandez.sqg.model.contents.HasContents
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ExplanationJsonDeserializerTest : NeedsToDeserializeContentsTest {

    //---- Attributes ----
    override var deserializer: JsonDeserializer<out HasContents> = createNormalDeserializer()
    override lateinit var groupOfContentsDeserializer: GroupOfContentsJsonDeserializer
    override lateinit var contents: GroupOfContents
    override val contentsKey: String
        get() = JsonKeys.Explanation.CONTENTS

    //---- Configuration ----
    @BeforeEach
    fun setup() {
        deserializer = createNormalDeserializer()
    }

    //---- Methods ----
    private fun createNormalDeserializer(): ExplanationJsonDeserializer {
        return ExplanationJsonDeserializer()
    }

    override fun createMockedDeserializer(groupOfContentsDeserializer: GroupOfContentsJsonDeserializer): JsonDeserializer<out HasContents> {
        return ExplanationJsonDeserializer(groupOfContentsDeserializer)
    }

    //---- Tests ----
    @Test
    fun problemJsonDeserializer_isInstanceOf_JsonDeserializerTest() {
        assertThat(ExplanationJsonDeserializer()).isInstanceOf(JsonDeserializer::class.java)
    }

//    @Test
//    fun deserialize_objectWithoutContents_returnsExplanationWithEmptyContentsTest() {
//        val json = JsonFixtures.EMPTY_JSON_OBJECT
//
//        val problem = deserializer.deserialize(json)
//
//        assertThat(problem.groupOfContents.size()).isZero()
//    }
//
//    @Test
//    fun deserialize_objectWithContentsAsArray_returnExplanationWithContentsTest() {
//        deserializer = createMockedDeserializer()
//        Mockito.`when`(groupOfContentsDeserializer.deserialize(anyString())).thenReturn(contents)
//        val json = """
//            {
//                "${JsonKeys.Explanation.CONTENTS}": []
//            }"""
//
//        val problem = deserializer.deserialize(json)
//
//        assertThat(problem.groupOfContents.contents())
//            .containsExactly(*contents.contents().toTypedArray())
//    }
//
//    @Test
//    fun deserialize_objectWithContentsInvalid_throwsExceptionTest() {
//        val json = """
//            {
//                "${JsonKeys.Explanation.CONTENTS}": {}
//            }"""
//
//        assertThrows<DeserializationException> { deserializer.deserialize(json) }
//    }

}