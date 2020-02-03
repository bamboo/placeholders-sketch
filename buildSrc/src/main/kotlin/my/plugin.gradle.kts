package my

val extension = extensions.create<Extension>("my")

tasks.register("printPlaceholders") {
    doLast {
        print("${project.name}.placeholders: ${extension.placeholders.getOrElse(emptyMap())}")
    }
}
