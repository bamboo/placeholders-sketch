# Placeholders Sketch

A small study on DSL design for map declarations.

## Values

1. Instant execution compatibility
2. Flexible wiring of keys, values and maps
3. Familiar syntax

## Proposal

1. Use a [MapProperty](https://docs.gradle.org/current/userguide/lazy_configuration.html#working_with_maps), see [Extension.placeholders](./buildSrc/src/main/kotlin/my/Extension.kt)

   ```kotlin
   interface Extension {
  
      val placeholders: MapProperty<String, Any>
   } 
   ```
2. Add Kotlin operator extensions as needed, see [kotlin-dsl/build.gradle.kts](./kotlin-dsl/build.gradle.kts)
   ```kotlin
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
   ```
   
Resulting in the following Kotlin DSL configuration syntax:

```kotlin
my {
    placeholders["foo"] = "bar"
    placeholders(
        "a" to "b",
        "c" to "d",
        "key" to true
    )
}
```

And the following Groovy DSL configuration syntax:
```groovy
my {
    placeholders = [a: 'b', c: 'd']
    placeholders.e = 'f'
    placeholders['g'] = 'h'
// Can be achieved via custom method OR Groovy decoration
//    placeholders i: 'j', k: 'l'
}
```
