package com.eldorne.bidule

import android.util.Log
import kotlin.properties.Delegates

class FactoryPattern {
    sealed class Currency {
        abstract val code: String

        class Euro : Currency() {
            override val code: String
                get() = "EURO"
        }

        class Dollar : Currency() {
            override val code: String
                get() = "USD"
        }

        override fun toString(): String {
            return code
        }

        companion object Factory {
            fun fromCountry(country: Country) = invoke(country)
            fun fromCode(code: String) = invoke(code)

            operator fun invoke(country: Country) =
                    when (country) {
                        Country.Spain -> Euro()
                        Country.UnitedStates -> Dollar()
                        else -> null
                    }

            operator fun invoke(code: String) =
                    when (code) {
                        "EUR" -> Euro()
                        "USD" -> Dollar()
                        else -> null
                    }
        }
    }

    enum class Country {
        UnitedStates, Spain, LALALAND
    }

    fun test() {
        arrayOf(Country.Spain, Country.LALALAND, Country.UnitedStates)
                .forEach {
                    val currency = Currency(it) //Same as Currency.fromCountry(it)
                    Log.d("FactoryPattern", """$it > ${currency.toString()}""")
                }

        arrayOf("EURO", "USD", "BONJOUR")
                .forEach {
                    val currency = Currency(it)//Same as Currency.fromCode(it)
                    Log.d("FactoryPattern", """$it > ${currency.toString()}""")
                }
    }
}

class OberserAndListenerPatternTest {
    interface TextChangedListener {
        fun onTextChanged(oldValue: String, newValue: String)
    }

    class TextView {
        val listener: TextChangedListener? = null
        var text: String by Delegates.observable(
                initialValue = "",
                onChange = {
                    _, oldValue, newValue -> listener?.onTextChanged(oldValue, newValue)
                })
    }


    fun test() {
        val textView = TextView()
        textView.text = ""
    }
}

class StatePatternTest {
    sealed class State {
        class Autorized(val token: String): State()
    }
}