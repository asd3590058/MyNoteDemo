package com.example.extensions

import com.example.network.BaseResponseBody
import com.squareup.moshi.*


/**
 * @author ：Tom Zhang - Android Developer
 * @date ：2021-12-01 18：35
 * 邮箱   ：JiaYang627@163.com / Tom@iappsasia.com
 */
fun genderMoshiBuild(): Moshi {
    return Moshi.Builder()
        .add(String::class.java, NullStringAdapter())
        .add(Int::class.java, NullIntAdapter())
        .add(Long::class.java, NullLongAdapter())
        .add(Double::class.java, NullDoubleAdapter())
        .add(Boolean::class.java, NullBooleanAdapter())
        .build()
}

inline fun <reified R : Any> toJsonByAny(value: R): String =
    genderMoshiBuild().adapter(R::class.java).run {
        toJson(value)
    }

inline fun <reified R : Any> toJsonByList(value: MutableList<R>): String {
    val types = Types.newParameterizedType(MutableList::class.java, R::class.java)
    return genderMoshiBuild().adapter<MutableList<R>>(types).run {
        toJson(value)
    }
}

inline fun <reified V : Any, reified R : Any> toJsonByMap(value: Map<V, R>): String? {
    val types = Types.newParameterizedType(Map::class.java, V::class.java, R::class.java)
    return genderMoshiBuild().adapter<Map<V, R>>(types).run {
        toJson(value)
    }
}


inline fun <reified R : Any> toJsonByBaseResult(value: BaseResponseBody<R>): String {
    val types = Types.newParameterizedType(BaseResponseBody::class.java, R::class.java)
    return genderMoshiBuild().adapter<BaseResponseBody<R>>(types).run {
        toJson(value)
    }
}

inline fun <reified R : Any> fromJsonByAny(value: String): R? =
    genderMoshiBuild().adapter(R::class.java).run {
        fromJson(value)
    }

inline fun <reified R : Any> fromJsonByMutableList(value: String): MutableList<R>? {
    val types = Types.newParameterizedType(MutableList::class.java, R::class.java)
    return genderMoshiBuild().adapter<MutableList<R>>(types).run {
        fromJson(value)
    }
}

inline fun <reified R : Any> fromJsonByList(value: String): List<R>? {
    val types = Types.newParameterizedType(MutableList::class.java, R::class.java)
    return genderMoshiBuild().adapter<MutableList<R>>(types).run {
        fromJson(value)
    }
}

inline fun <reified V : Any, reified R : Any> fromJsonByMap(value: String): Map<V, R>? {
    val types = Types.newParameterizedType(Map::class.java, V::class.java, R::class.java)
    return genderMoshiBuild().adapter<Map<V, R>>(types).run {
        fromJson(value)
    }
}

inline fun <reified R : Any> fromJsonByBaseResponseBody(value: String): BaseResponseBody<R>? {
    val types = Types.newParameterizedType(BaseResponseBody::class.java, R::class.java)
    return genderMoshiBuild().adapter<BaseResponseBody<R>>(types).run {
        fromJson(value)
    }
}

internal class NullStringAdapter : JsonAdapter<String>() {
    override fun fromJson(reader: JsonReader): String? {
        try {
            if (reader.peek() != JsonReader.Token.NULL) {
                return reader.nextString()
            }
            reader.nextNull<Unit>()
        } catch (e: Exception) {

        }
        return ""
    }

    override fun toJson(writer: JsonWriter, value: String?) {
        writer.value(value)
    }
}

internal class NullIntAdapter : JsonAdapter<Int>() {
    override fun fromJson(reader: JsonReader): Int {
        try {
            if (reader.peek() != JsonReader.Token.NULL || reader.peek().toString().isNotBlank()) {
                return reader.nextInt()
            }
            reader.nextNull<Unit>()
        } catch (e: Exception) {

        }
        return 0
    }

    override fun toJson(writer: JsonWriter, value: Int?) {
        writer.value(value)
    }
}

internal class NullLongAdapter : JsonAdapter<Long>() {
    override fun fromJson(reader: JsonReader): Long {
        try {
            if (reader.peek() != JsonReader.Token.NULL || reader.peek().toString().isNotBlank()) {
                return reader.nextLong()
            }
            reader.nextNull<Unit>()
        } catch (e: Exception) {

        }
        return 0L
    }

    override fun toJson(writer: JsonWriter, value: Long?) {
        writer.value(value)
    }
}

internal class NullDoubleAdapter : JsonAdapter<Double>() {
    override fun fromJson(reader: JsonReader): Double {
        try {
            if (reader.peek() != JsonReader.Token.NULL || reader.peek().toString().isNotBlank()) {
                return reader.nextDouble()
            }
            reader.nextNull<Unit>()
        } catch (e: Exception) {

        }
        return 0.00
    }

    override fun toJson(writer: JsonWriter, value: Double?) {
        writer.value(value)
    }
}

internal class NullBooleanAdapter : JsonAdapter<Boolean>() {
    override fun fromJson(reader: JsonReader): Boolean {
        try {
            if (reader.peek() != JsonReader.Token.NULL || reader.peek().toString().isNotBlank()) {
                return reader.nextBoolean()
            }
            reader.nextNull<Unit>()
        } catch (e: Exception) {

        }
        return false
    }

    override fun toJson(writer: JsonWriter, value: Boolean?) {
        writer.value(value)
    }
}
