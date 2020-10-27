/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package com.kotlinerskt.cli.giskt

import com.github.ajalt.clikt.core.subcommands
import com.kotlinerskt.cli.giskt.commands.Giskt
import com.kotlinerskt.cli.giskt.commands.GisktCreate
import com.kotlinerskt.cli.giskt.commands.GisktList

fun main(args: Array<String>) {
    Giskt().subcommands(
        GisktCreate(),
        GisktList(),
    ).main(args)
}
