package dev.chainmail.dragapult.args

import dev.chainmail.dragapult.log.expects
import dev.chainmail.dragapult.model.OutputFileFormat
import kotlin.system.exitProcess

data class OutputFormat(val format: OutputFileFormat) {

    companion object : ArgumentDefinition<OutputFormat> {
        override val callSign = "-oF"
        override fun getInstance(args: Array<String>): OutputFormat {
            val index = args.indexOf(callSign)
            val formatString = args.getOrNull(index + 1)
            val format = OutputFileFormat.findValue(formatString)

            if (index < 0) {
                return OutputFormat(OutputFileFormat.JSON)
            }

            if (format == null) {
                "output format" expects "!= null" given format
                exitProcess(1)
            }

            return OutputFormat(format)
        }
    }


}