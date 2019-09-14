package io.example.custom.detekt.extension

import org.gradle.api.Project
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.plugins.quality.CodeQualityExtension

open class ZenLintExtension(project: Project) : CodeQualityExtension() {
    var src: ConfigurableFileCollection = project.layout.configurableFiles()
}
