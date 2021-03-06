package com.haurbano.apksizeplugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class APKSizePlugin implements Plugin<Project> {
    static def formatSize(fileSizeInBytes) {
        def fileSizeInKB = (fileSizeInBytes / 1024).round(2)
        def fileSizeInMB = (fileSizeInKB / 1024).round(2)
        if (fileSizeInKB > 1000) {
            return "$fileSizeInMB MB"
        } else {
            return "$fileSizeInKB KB"
        }
    }

    @Override
    void apply(Project target) {
        target.task('apkSize') {
            doLast {
                project.android.applicationVariants.all { variant ->
                    variant.outputs.each { output ->
                        def apkSize = output.outputFile.length()
                        def readableSize = formatSize(apkSize)
                        println("$variant.name Apk Size: $readableSize")
                    }
                }
            }
        }
    }
}