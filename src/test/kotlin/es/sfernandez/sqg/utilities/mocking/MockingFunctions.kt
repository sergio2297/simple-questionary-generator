package es.sfernandez.sqg.utilities.mocking

import java.util.function.Supplier

inline fun <reified MOCK> generateMocks(mockGenerator: Supplier<MOCK>, times: Int): Array<MOCK> {
    return generateSequence(0) { it + 1}
        .take(times)
        .map { mockGenerator.get() }
        .toList()
        .toTypedArray()
}