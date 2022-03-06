package it.paganellif.gitlabcli.cmd

import it.paganellif.gitlabcli.cmd.project.GetProjectCmd
import it.paganellif.gitlabcli.cmd.project.GetVarProjectCmd
import picocli.CommandLine
import java.util.concurrent.Callable

@CommandLine.Command(name = "prj",
    mixinStandardHelpOptions = true,
    version = ["0.0.1"],
    description = ["Gitlab Project Command"],
    showAtFileInUsageHelp = true,
    subcommands = [GetProjectCmd::class, GetVarProjectCmd::class])
class MainCmd : Callable<Int> {
    // TODO: mettere qua tutte le opzioni/parametri comuni a tutti( --help, --version, -debug, ...)
    override fun call(): Int { return 0 }
}