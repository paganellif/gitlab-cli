package it.paganellif.gitlabcli.cmd.project

import it.paganellif.gitlabcli.api.ProjectApi
import kotlinx.coroutines.runBlocking
import picocli.CommandLine.Option
import picocli.CommandLine.Command
import picocli.CommandLine.Parameters
import java.util.concurrent.Callable

@Command(name = "prj-get",
    mixinStandardHelpOptions = true,
    description = ["Gitlab Project Get Command"])
class GetProjectCmd : Callable<Int> {

    @Parameters(index = "0", description = ["The project url/ID (i.e. group/subgroup/name)"], arity = "1")
    lateinit var projectRef: String

    @Option(names = ["--simple", "-s"], description = ["simple output"], arity = "0..1")
    var simple: Boolean = false

    override fun call(): Int {
        val api = ProjectApi("https://gitlab.com/api/v4/")
        var option = "?"

        if(simple)
            option += "simple=True"

        runBlocking {
            println(api.getProject(projectRef, option))
        }
        return 1
    }
}