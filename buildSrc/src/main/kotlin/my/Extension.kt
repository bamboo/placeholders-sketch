package my

import org.gradle.api.provider.MapProperty

interface Extension {

    val placeholders: MapProperty<String, Any>
}