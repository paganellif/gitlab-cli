package it.paganellif.gitlabcli.cmd.project

import it.paganellif.gitlabcli.api.ProjectApi
import kotlinx.coroutines.runBlocking
import picocli.CommandLine.Option
import picocli.CommandLine.Command
import picocli.CommandLine.Parameters
import java.util.concurrent.Callable

@Command(name = "prj-var",
    mixinStandardHelpOptions = true,
    description = ["Gitlab Project Var Command"])
class GetVarProjectCmd : Callable<Int> {

    @Parameters(index = "0", description = ["The project url/ID (i.e. group/subgroup/name)"], arity = "1")
    lateinit var projectRef: String

    @Option(names = ["--key", "-k"], description = ["var key"], arity = "0..1")
    var key: String = ""

    override fun call(): Int {
        val api = ProjectApi("https://gitlab.com/api/v4/")

        runBlocking {
            println(api.getProjectVar(projectRef, key))
        }
        return 1
    }
}