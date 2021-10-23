package com.fsm.pokedex.api.adapters

import com.fsm.pokedex.data.model.Pokemon
import com.squareup.moshi.*

class PokemonGQLJsonAdapter : JsonAdapter<List<Pokemon>>() {

    @FromJson
    override fun fromJson(reader: JsonReader): List<Pokemon>? {
        val moshi = Moshi.Builder().build()
        //Entering Json object
        reader.beginObject()
        //Skip "data" name
        reader.skipName()
        //Enter data object
        reader.beginObject()
        //Skip the whatever name of the list is.
        reader.skipName()
        val type = Types.newParameterizedType(List::class.java, Pokemon::class.java)
        val jsonAdapter: JsonAdapter<List<Pokemon>> = moshi.adapter(type)
        val list = jsonAdapter.fromJson(reader.nextSource())
        reader.endObject()
        reader.endObject()
        return list
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: List<Pokemon>?) {
        writer.beginObject().beginArray().jsonValue(value).endArray().endObject()
    }
}