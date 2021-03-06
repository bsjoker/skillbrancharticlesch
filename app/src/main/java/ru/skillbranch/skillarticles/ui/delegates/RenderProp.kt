package ru.skillbranch.skillarticles.ui.delegates

import ru.skillbranch.skillarticles.ui.base.Binding
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class RenderProp<T>(
    var value: T,
    isNeedInit: Boolean = true, // for not looping DarkMode change
    private val onChange: ((T) -> Unit)? = null
) : ReadWriteProperty<Binding, T> {
    private val listeners : MutableList<() -> Unit> = mutableListOf()

    init {
        if (isNeedInit) onChange?.invoke(value)
    }

    override fun getValue(thisRef: Binding, property: KProperty<*>): T = value

    override fun setValue(thisRef: Binding, property: KProperty<*>, value: T) {
        if (value == this.value) return
        this.value = value
        onChange?.invoke(this.value)
        if (listeners.isNotEmpty()) listeners.forEach { it.invoke() }
    }

    // Register additional listener
    fun addListener(listener: () -> Unit) {
        listeners.add(listener)
    }
}

class ObserveProp<T : Any>(private var value : T, private val onChange: ((T) -> Unit)? = null) {
    // Provide delegate (when "by" call)
    operator fun provideDelegate(
        thisRef: Binding,
        property: KProperty<*>
    ) : ReadWriteProperty<Binding, T> {
        val delegate = RenderProp(value, true, onChange)
        registerDelegate(thisRef, property.name, delegate)
        return delegate
    }

    // Register new delegate for property in Binding
    private fun registerDelegate(thisRef: Binding, name: String, delegate: RenderProp<T>) {
        thisRef.delegates[name] = delegate
    }
}