package es.sfernandez.sqg.deserializer.json.question

import es.sfernandez.sqg.deserializer.json.JsonDeserializer
import es.sfernandez.sqg.deserializer.json.JsonFixtures
import es.sfernandez.sqg.deserializer.json.question.contents.GroupOfContentsJsonDeserializer
import es.sfernandez.sqg.deserializer.logs.DeserializationLogUtilsForTests
import es.sfernandez.sqg.beans.contents.GroupOfContents
import es.sfernandez.sqg.beans.contents.HasContents
import es.sfernandez.sqg.beans.contents.UnknownContent
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito

interface NeedsToDeserializeGroupOfContentsTest {

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
    fun afterDeserialize_objectWithoutContents_logsHaveErrorTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsErrorWithWord(deserializer, contentsKey)
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
    fun deserialize_objectWithInvalidContents_returnsHasContentsWithEmptyContentsTest() {
        val json = """
            {
                "$contentsKey": {}
            }"""

        val hasContents = deserializer.deserialize(json)

        Assertions.assertThat(hasContents.groupOfContents.size()).isZero()
    }

    @Test
    fun afterDeserialize_objectWithInvalidContents_logsHaveErrorTest() {
        val json = """
            {
                "$contentsKey": {}
            }"""

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsErrorWithWord(deserializer, contentsKey)
    }

}