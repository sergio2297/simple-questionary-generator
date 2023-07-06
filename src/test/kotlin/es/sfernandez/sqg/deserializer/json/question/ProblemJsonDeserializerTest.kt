package es.sfernandez.sqg.deserializer.json.question

import es.sfernandez.sqg.deserializer.DeserializationException
import es.sfernandez.sqg.deserializer.json.JsonDeserializer
import es.sfernandez.sqg.deserializer.json.JsonFixtures
import es.sfernandez.sqg.deserializer.json.JsonKeys
import es.sfernandez.sqg.deserializer.json.question.contents.GroupOfContentsJsonDeserializer
import es.sfernandez.sqg.model.contents.GroupOfContents
import es.sfernandez.sqg.model.contents.UnknownContent
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito

class ProblemJsonDeserializerTest {

    //---- Attributes ----
    private lateinit var deserializer : ProblemJsonDeserializer

    private lateinit var groupOfContentsDeserializer: GroupOfContentsJsonDeserializer

    //---- Fixtures ----
    private val contents = GroupOfContents()

    //---- Configuration ----
    @BeforeEach
    fun setup() {
        deserializer = createNormalDeserializer()
        contents.add(*generateSequence { UnknownContent() }.take(5).toList().toTypedArray())
    }

    //---- Methods ----
    private fun createNormalDeserializer(): ProblemJsonDeserializer {
        return ProblemJsonDeserializer()
    }

    private fun createMockedDeserializer(): ProblemJsonDeserializer {
        groupOfContentsDeserializer = Mockito.mock(GroupOfContentsJsonDeserializer::class.java)
        return ProblemJsonDeserializer(groupOfContentsDeserializer)
    }

    //---- Tests ----
    @Test
    fun problemJsonDeserializer_isInstanceOf_JsonDeserializerTest() {
        assertThat(ProblemJsonDeserializer()).isInstanceOf(JsonDeserializer::class.java)
    }

    @Test
    fun deserialize_objectWithoutContents_returnsProblemWithEmptyContentsTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT

        val problem = deserializer.deserialize(json)

        assertThat(problem.groupOfContents.size()).isZero()
    }

    @Test
    fun deserialize_objectWithContentsAsArray_returnProblemWithContentsTest() {
        deserializer = createMockedDeserializer()
        Mockito.`when`(groupOfContentsDeserializer.deserialize(anyString())).thenReturn(contents)
        val json = """
            {
                "${JsonKeys.Problem.CONTENTS}": []
            }"""

        val problem = deserializer.deserialize(json)

        assertThat(problem.groupOfContents.contents())
            .containsExactly(*contents.contents().toTypedArray())
    }

    @Test
    fun deserialize_objectWithContentsInvalid_throwsExceptionTest() {
        val json = """
            {
                "${JsonKeys.Problem.CONTENTS}": {}
            }"""

        assertThrows<DeserializationException> { deserializer.deserialize(json) }
    }

}