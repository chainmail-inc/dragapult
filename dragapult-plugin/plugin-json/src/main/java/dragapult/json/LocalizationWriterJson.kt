package dragapult.json

import com.google.auto.service.AutoService
import dragapult.core.*
import org.json.JSONObject
import java.io.File

class LocalizationWriterJson(
    private val file: File
) : LocalizationWriter {

    override fun write(values: Sequence<Pair<Key, Value>>) {
        val json = JSONObject()
        for ((key, value) in values) {
            json.put(key, value)
        }
        file.writer().use {
            json.write(it)
        }
    }

    // ---

    @AutoService(LocalizationWriter.Factory::class)
    class Factory : LocalizationWriter.Factory {

        override val type: LocalizationType
            get() = LocalizationTypeJson

        override fun create(file: File): LocalizationWriter {
            var writer: LocalizationWriter
            writer = LocalizationWriterJson(file)
            writer = LocalizationWriterReplacing(writer, "%@", "%s")
            writer = LocalizationWriterEmptyFiltering(writer)
            return writer
        }

    }

}