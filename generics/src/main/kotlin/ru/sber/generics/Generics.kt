package ru.sber.generics

// 1.
fun <T> compare(p1: Pair<T, T>, p2: Pair<T, T>): Boolean {

    return p1.first == p2.first && p2.second == p1.second
}

// 2.
fun <E : Comparable<E>> countGreaterThan(anArray: Array<E>, elem: E): Int {
    var cntGreaterNums = 0
    anArray.forEach {
        if(it > elem) cntGreaterNums++
    }
    return cntGreaterNums
}

// 3.
class Sorter <T: Comparable<T>> {
    val list: MutableList<T> = mutableListOf()

    fun add(value: T) {
        list.add(value)
        list.sort()
    }
}

// 4.
class Stack <T> {
    val list: MutableList<T> = mutableListOf()

    fun push(elem: T){
        list.add(0,elem)
    }

    fun pop(): T {
        return list.removeAt(0)
    }

    fun isEmpty():Boolean = list.isNullOrEmpty()
}