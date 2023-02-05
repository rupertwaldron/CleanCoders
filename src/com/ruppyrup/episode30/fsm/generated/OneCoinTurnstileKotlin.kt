package com.ruppyrup.episode30.fsm.generated

abstract class OneCoinTurnstileKotlin {
    abstract fun unhandledTransition(state: String?, event: String?)
    private enum class State { Locked, Unlocked }
    private enum class Event { Coin, Pass }

    private var state = State.Locked
    private fun setState(s: State) {
        state = s
    }

    fun Coin() {
        handleEvent(Event.Coin)
    }

    fun Pass() {
        handleEvent(Event.Pass)
    }

    private fun handleEvent(event: Event) {
        when (state) {
            State.Locked -> {
                when (event) {
                    Event.Coin -> {
                        setState(State.Unlocked)
                        alarmOff()
                        unlock()
                    }

                    Event.Pass -> {
                        setState(State.Locked)
                        alarmOn()
                    }

                    else -> unhandledTransition(state.name, event.name)
                }
            }

            State.Unlocked -> {
                when (event) {
                    Event.Coin -> {
                        setState(State.Unlocked)
                        thankyou()
                    }

                    Event.Pass -> {
                        setState(State.Locked)
                        lock()
                    }

                    else -> unhandledTransition(state.name, event.name)
                }
            }
        }
    }

    protected abstract fun thankyou()
    protected abstract fun unlock()
    protected abstract fun alarmOn()
    protected abstract fun lock()
    protected abstract fun alarmOff()
}
