package spoonacular.infrastructure

import com.squareup.moshi.*
import java.io.IOException
import java.math.BigDecimal
import java.util.*


class BigDecimalAdapter : JsonAdapter<BigDecimal>() {
    @Synchronized
    @Throws(IOException::class)
    override fun fromJson(reader: JsonReader): BigDecimal {
        val string = reader.nextString()
        return BigDecimal(string)
    }

    @Synchronized
    @Throws(IOException::class)
    override fun toJson(writer: JsonWriter, value: BigDecimal?) {
        val string = value.toString()
        writer.value(string)
    }
}

object Serializer {
    @JvmStatic
    val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .add(BigDecimal::class.java, BigDecimalAdapter().nullSafe())
        .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
        .build()
}
