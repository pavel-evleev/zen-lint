package io.example.custom.detekt.plugin

import io.gitlab.arturbosch.detekt.DetektPlugin
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import io.example.custom.detekt.extension.ZenLintExtension
import io.example.custom.detekt.tasks.CopyResourcesTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.language.base.plugins.LifecycleBasePlugin

open class CustomDetektPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.plugins.apply(DetektPlugin::class.java)
        val ext = project.extensions.getByType(DetektExtension::class.java)
        val detekt = project.tasks.getByName("detekt")

        val zenLint = project.tasks.create("zenLint") {
            it.group = LifecycleBasePlugin.VERIFICATION_GROUP
        }
        val zenExtension = project.extensions.create("zenLint", ZenLintExtension::class.java, project)

        val zenRules = project.tasks.create("zenRulesInit", CopyResourcesTask::class.java) {
            it.group = LifecycleBasePlugin.VERIFICATION_GROUP
            it.detektPlugin = ext
            it.zenExtension = zenExtension
        }

        zenLint.dependsOn.add(detekt)
        detekt.dependsOn.add(zenRules)
    }

}
