package com.br.repogit.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

open class Event<out T>(val data: T) {

    var hasBeenHandled = false
        protected set

    fun trigger(body: Event<T>.() -> Unit) {
        if (hasBeenHandled.not()) {
            hasBeenHandled = true
            body(this)
        }
    }
}

class SimpleEvent : Event<Any?>(null)

fun <T : Event<*>> LiveData<T>.subscribe(owner: LifecycleOwner, body: T.() -> Unit) {
    observe(owner, Observer {
        it?.trigger { body(it) }
    })
}

fun MutableLiveData<SimpleEvent>.triggerEvent() {
    this.value = SimpleEvent()
}

fun <T > MutableLiveData<Event<T>>.triggerEvent(data: T) {
    this.value = Event(data)
}