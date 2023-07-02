package es.sfernandez.sqg.deserializer.json

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.module.SimpleModule
import es.sfernandez.sqg.deserializer.Deserializer

abstract class JsonDeserializer<T> : Deserializer<T> {

    //---- Attributes ----
    private val mappedClass : Class<T>
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

}