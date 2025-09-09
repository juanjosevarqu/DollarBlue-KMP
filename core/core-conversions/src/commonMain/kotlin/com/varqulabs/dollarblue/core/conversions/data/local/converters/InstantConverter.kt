package com.varqulabs.dollarblue.core.conversions.data.local.converters

import androidx.room.TypeConverter
import kotlin.time.Instant

class InstantConverter {

    @TypeConverter
    fun fromInstant(v: Instant): Long = v.toEpochMilliseconds()

    @TypeConverter
    fun toInstant(ms: Long): Instant = Instant.fromEpochMilliseconds(ms)
}