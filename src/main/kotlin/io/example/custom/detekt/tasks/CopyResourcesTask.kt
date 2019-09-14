package io.example.custom.detekt.tasks

import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import io.example.custom.detekt.extension.ZenLintExtension
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class CopyResourcesTask : DefaultTask() {

    lateinit var detektPlugin: DetektExtension
    lateinit var zenExtension: ZenLintExtension

    @TaskAction
    fun copyResources() {
        val text = javaClass.classLoader.getResource("detekt-config.yml").readText()
        val jar = javaClass.classLoader.getResource("detektRules.jar").readBytes()

        project.file("${project.buildDir.path}/tmp").mkdirs()

        val configFile = project.file("${project.projectDir}/build/tmp/detekt-config.yml")
        val configJar = project.file("${project.projectDir}/build/tmp/detektRules.jar")

        configFile.writeText(text)
        configJar.writeBytes(jar)

        detektPlugin.config.setFrom(configFile)
        detektPlugin.plugins = configJar.path

        if (!zenExtension.src.isEmpty) {
            detektPlugin.input.setFrom(project.provider { zenExtension.src.filter { it.exists() } })
        }
    }

}
