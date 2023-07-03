package es.sfernandez.sqg.deserializer.json

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.module.SimpleModule
import es.sfernandez.sqg.deserializer.DeserializationException
import es.sfernandez.sqg.deserializer.Deserializer

abstract class JsonDeserializer<T> : Deserializer<T> {

    //---- Attributes ----
    protected val mappedClass : Class<T>
    private val mapper : ObjectMapper

    //---- Constructor ----
    protected constructor(mappedClass : Class<T>) {
        this.mappedClass = mappedClass
        this.mapper = createMapper(mappedClass)
    }

    private fun createMapper(clazz : Class<T>) : ObjectMapper {
        val mapper = ObjectMapper()
        val module = SimpleModule()
        module.addDeserializer(clazz, createDeserializer())
        mapper.registerModule(module)
        return mapper
    }
    protected abstract fun createDeserializer() : StdDeserializer<T>

    //---- Methods ----
    final override fun deserialize(text : String) : T {
        return mapper.readValue(text, mappedClass)
    }

    /**
     * Extracts the json tree from the given JsonParser and returns it as a JsonNode
     *
     * @param parser Parser which contains the JsonNode to extract
     * @return the json tree from the given JsonParser as a JsonNode
     * @throws DeserializationException if parser is null
     */
    protected fun extractJsonNode(parser: JsonParser?) : JsonNode {
        if(parser == null) throw DeserializationException("Error. Can not deserialize $mappedClass because JsonParser is null")
        return parser.codec.readTree(parser)
    }

}