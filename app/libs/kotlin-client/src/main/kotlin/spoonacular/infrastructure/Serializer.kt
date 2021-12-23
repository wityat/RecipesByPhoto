package spoonacular.infrastructure

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.Rfc3339DateJsonAdapter
import java.util.*

object BigDecimalAdapter {
    @FromJson fun fromJson(string: String) = BigDecimal(string)

    @ToJson fun toJson(value: BigDecimal) = value.toString()
}

object Serializer {
    @JvmStatic
    val moshi: Moshi = Moshi.Builder()
        .add(BigDecimalAdapter)
        .add(KotlinJsonAdapterFactory())
        .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
        .build()
}
