package pt.cagica.globalsharesapplication.api.typeadapters

import com.google.gson.*
import java.lang.reflect.Type
import java.util.*

class DateTypeAdapter : JsonDeserializer<Any> {

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Date {

        val s = json.asJsonPrimitive.asString

        val s1 = s.substring(6, s.length - 2)

        val l = java.lang.Long.parseLong(s1)
        return Date(l)
    }
}