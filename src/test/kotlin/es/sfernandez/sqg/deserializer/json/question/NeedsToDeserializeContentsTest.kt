package es.sfernandez.sqg.deserializer.json.question

import es.sfernandez.sqg.deserializer.DeserializationException
import es.sfernandez.sqg.deserializer.json.JsonDeserializer
import es.sfernandez.sqg.deserializer.json.JsonFixtures
import es.sfernandez.sqg.deserializer.json.question.contents.GroupOfContentsJsonDeserializer
import es.sfernandez.sqg.model.contents.GroupOfContents
import es.sfernandez.sqg.model.contents.HasContents
import es.sfernandez.sqg.model.contents.UnknownContent
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.ArgumentMatchers
import org.mockito.Mockito

interface NeedsToDeserializeContentsTest {

    //---- Attributes ----
    var deserializer: JsonDeserializer<out HasContents>
    var groupOfContentsDeserializer: GroupOfContentsJsonDeserializer
    var contents: GroupOfContents
    val contentsKey: String

    //---- Configuration ----
    @BeforeEach
    fun canDeserializeContentsSetup() {
        groupOfContentsDeserializer = Mockito.mock(GroupOfContentsJsonDeserializer::class.java)
        contents = generateGroupOfContents()
    }

    //---- Methods ----
    private fun generateGroupOfContents(): GroupOfContents {
        val group = GroupOfContents()
        group.add(*generateSequence { UnknownContent() }.take(5).toList().toTypedArray())
        return group
    }

    fun createMockedDeserializer(groupOfContentsDeserializer: GroupOfContentsJsonDeserializer): JsonDeserializer<out HasContents>

    //---- Tests ----
    @Test
    fun deserialize_objectWithoutContents_returnsHasContentsWithEmptyContentsTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT

        val hasContents = deserializer.deserialize(json)

        Assertions.assertThat(hasContents.groupOfContents.size()).isZero()
    }

    @Test
    fun deserialize_objectWithContentsAsArray_returnHasContentsWithContentsTest() {
        deserializer = createMockedDeserializer(groupOfContentsDeserializer)
        Mockito.`when`(groupOfContentsDeserializer.deserialize(ArgumentMatchers.anyString())).thenReturn(contents)
        val json = """
            {
                "$contentsKey": []
            }"""

        val hasContents = deserializer.deserialize(json)

        Assertions.assertThat(hasContents.groupOfContents.contents())
            .containsExactly(*contents.contents().toTypedArray())
    }

    @Test
    fun deserialize_objectWithContentsInvalid_throwsExceptionTest() {
        val json = """
            {
                "$contentsKey": {}
            }"""

        assertThrows<DeserializationException> { deserializer.deserialize(json) }
    }

}