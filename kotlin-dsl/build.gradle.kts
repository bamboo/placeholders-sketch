plugins {
    my.plugin
}

/**
 * Enables the `map[key] = value` syntax on [MapProperty] values.
 */
operator fun <K, V> MapProperty<K, V>.set(k: K, v: V) =
    put(k, v)

/**
 * Enables the `map("k1" to "v1", "kn" to "vn")` syntax on [MapProperty] values.
 */
operator fun <K, V> MapProperty<K, V>.invoke(vararg entries: Pair<K, V>) =
    entries.forEach { (k, v) -> this@invoke.put(k, v) }

my {
    placeholders["foo"] = "bar"
    placeholders(
        "a" to "b",
        "c" to "d",
        "key" to true
    )
}

