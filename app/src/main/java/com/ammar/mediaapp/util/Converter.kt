package com.ammar.mediaapp.util

import androidx.room.TypeConverter
import com.ammar.mediaapp.data.model.Source

class Converter {

    @TypeConverter
    fun fromSource(source: Source): String {
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source {
        return Source(name, name)
    }
}