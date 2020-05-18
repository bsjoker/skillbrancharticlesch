package ru.skillbranch.skillarticles.data.delegates

import ru.skillbranch.skillarticles.data.local.PrefManager
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class PrefDelegate<T>(private val defaultValue: T) : ReadWriteProperty<PrefManager, T?> {
    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: PrefManager, property: KProperty<*>): T? {
        val preferences = thisRef.preferences
        return when (defaultValue) {
            is Boolean -> preferences.getBoolean(property.name, defaultValue) as T
            is String -> preferences.getString(property.name, defaultValue) as T
            is Float -> preferences.getFloat(property.name, defaultValue) as T
            is Int -> preferences.getInt(property.name, defaultValue) as T
            is Long -> preferences.getLong(property.name, defaultValue) as T
            else -> error("Unexpected type")
        }
    }

    override fun setValue(thisRef: PrefManager, property: KProperty<*>, value: T?) {
        thisRef.preferences.edit().apply {
            when (value) {
                is Boolean -> putBoolean(property.name, value)
                is String -> putString(property.name, value)
                is Float -> putFloat(property.name, value)
                is Int -> putInt(property.name, value)
                is Long -> putLong(property.name, value)
                else -> error("Unexpected type")
            }
        }.apply()
    }
}