package com.eldorne.bidule

import java.util.*

class Test1 {
    fun start(): String {
        return "OK"
    }
}

class Test2 {
    fun toJSON(collection: Collection<Int>): String {
        val sb = StringBuilder()
        sb.append("[")
        val iterator = collection.iterator()
        while (iterator.hasNext()) {
            val element = iterator.next()
            sb.append(element)
            if (iterator.hasNext()) {
                sb.append(", ")
            }
        }
        sb.append("]")
        return sb.toString()
    }
}

class Test3 {
    fun joinOptions(options: Collection<String>): String {
        return options.joinToString(prefix="[", postfix="]");
    }
}

class Test4 {
    fun foo(name: String, number: Int = 42, toUpperCase: Boolean = false): String {
        return (if (toUpperCase) name.toUpperCase() else name) + number
    }
}

class Test5 {
    fun containsEven(collection: Collection<Int>): Boolean {
        collection.forEach {
            if (it % 2 == 0) {
                return true
            }
        }

        return false
    }
}

class Test6 {
    fun getPattern(): String {
        val month = "(JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)"
        return """\d{2} $month \d{4}"""
    }

}

class Test7{
    data class Person(val name: String, val age: Int)
}

class Test8 {
    class Client (val personalInfo: PersonalInfo?)
    class PersonalInfo (val email: String?)
    interface Mailer {
        fun sendMessage(email: String, message: String)
    }
    fun sendMessageToClient(client: Client?, message: String?, mailer: Mailer) {
        message?.let {
            client?.personalInfo?.email?.let {
                mailer.sendMessage(it, message)
            }
        }
    }
}

class Test9 {
    fun eval(expr: Expr): Int {
        when (expr) {
            is Num -> return expr.value
            is Sum -> return eval(expr.left) + eval(expr.right)
            else -> throw IllegalArgumentException("Unknown expression")
        }
    }

    interface Expr
    class Num(val value: Int) : Expr
    class Sum(val left: Expr, val right: Expr) : Expr
}

class Test10 {
    fun Int.r(): RationalNumber {
        return RationalNumber(this, 1)
    }
    fun Pair<Int, Int>.r(): RationalNumber {
        return RationalNumber(first, second)
    }

    data class RationalNumber(val numerator: Int, val denominator: Int)
}

class Test11 {
    fun getList(): List<Int> {
        val arrayList = arrayListOf(1, 5, 2)

        //arrayList.sortWith(Comparator { first, second -> (second - first) })
        //arrayList.sortWith(compareByDescending { it })
        Collections.sort(arrayList, Comparator { first, second -> (second - first) })

        return arrayList
    }
}

class Test12 {
    fun getList(): List<Int> {
        val arrayList = arrayListOf(1, 5, 2)
        Collections.sort(arrayList, { x, y -> y - x })
        return arrayList
    }
}

class Test13 {
    fun getList(): List<Int> {
        //return arrayListOf(1, 5, 2).sortedByDescending { it }
        return arrayListOf(1, 5, 2).sortedDescending()
    }
}

class Test14 {
    data class MyDate(val year: Int,
                      val month: Int,
                      val dayOfMonth: Int) : Comparable<MyDate> {

        override fun compareTo(other: MyDate): Int = compareManually(other)

        private fun compareManually(other: MyDate): Int {
            val yearGap = this.year - other.year
            val monthGap = this.month - other.month
            val dayGap = this.dayOfMonth - other.dayOfMonth

            when {
                yearGap != 0 -> return yearGap
                monthGap != 0 -> return monthGap
                dayGap != 0 -> return dayGap
                else -> return 0
            }
        }

        private fun compareWithCalendar(other: MyDate): Int {
            val calendarIt = Calendar.getInstance()
            calendarIt.set(Calendar.YEAR, this.year)
            calendarIt.set(Calendar.MONTH, this.month)
            calendarIt.set(Calendar.DAY_OF_MONTH, this.dayOfMonth)

            val calendarOther = Calendar.getInstance()
            calendarOther.set(Calendar.YEAR, other.year)
            calendarOther.set(Calendar.MONTH, other.month)
            calendarOther.set(Calendar.DAY_OF_MONTH, other.dayOfMonth)

            return calendarIt.compareTo(calendarOther)
        }
    }
}
