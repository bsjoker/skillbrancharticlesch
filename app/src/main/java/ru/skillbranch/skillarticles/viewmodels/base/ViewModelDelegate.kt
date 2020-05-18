package ru.skillbranch.skillarticles.viewmodels.base

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.Constructor
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class ViewModelDelegate<T : ViewModel>(private val clazz: Class<T>, private val arg: Any?) :
    ReadOnlyProperty<FragmentActivity, T> {
    lateinit var delegateViewModel: T

    override fun getValue(thisRef: FragmentActivity, property: KProperty<*>): T {
        return if (!::delegateViewModel.isInitialized) {
            ViewModelProvider(thisRef, DelegateVMFactory(arg)).get(clazz)
        } else {
            delegateViewModel
        }
    }

    // From neikist
    @Suppress("UNCHECKED_CAST")
    class DelegateVMFactory(private val params: Any?) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            val constructor: Constructor<T> = modelClass
                .constructors
                .firstOrNull {
                    it.parameterTypes.size == 1 && Any::class.java.isAssignableFrom(it.parameterTypes[0])
                } as? Constructor<T> ?: error("Cannot find need constructor")
            return constructor.newInstance(params)
        }
    }
}