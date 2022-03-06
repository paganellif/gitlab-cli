package it.paganellif.gitlabcli

import it.paganellif.gitlabcli.cmd.MainCmd
import picocli.CommandLine
import kotlin.system.exitProcess

fun main(args: Array<String>) : Unit = exitProcess(CommandLine(MainCmd()).execute(*args))
